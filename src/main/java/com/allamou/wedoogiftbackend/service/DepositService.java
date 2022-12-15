package com.allamou.wedoogiftbackend.service;


import com.allamou.wedoogiftbackend.exception.DepositNotFoundException;
import com.allamou.wedoogiftbackend.exception.UserNotFoundException;
import com.allamou.wedoogiftbackend.model.Deposit;
import com.allamou.wedoogiftbackend.model.User;
import com.allamou.wedoogiftbackend.repository.DepositRepository;
import com.allamou.wedoogiftbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {

    private DepositRepository depositRepository;

    private UserRepository userRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository, UserRepository userRepository) {
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
    }

    public List<Deposit> getDepositsOfUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Utilisateur avec l'id " + userId + " est introuvable en bdd."));
        return depositRepository.findAllByUser(user);
    }

    public Deposit getDepositByUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Utilisateur avec l'id " + userId + " est introuvable en bdd."));
        return depositRepository.findByUser(user).orElseThrow(() -> new DepositNotFoundException("Le dépot est introuvable"));
    }
}
