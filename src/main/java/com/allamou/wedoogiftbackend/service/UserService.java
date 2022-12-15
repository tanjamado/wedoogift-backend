package com.allamou.wedoogiftbackend.service;

import com.allamou.wedoogiftbackend.dto.UserBalanceResponse;
import com.allamou.wedoogiftbackend.exception.UserNotFoundException;
import com.allamou.wedoogiftbackend.model.User;
import com.allamou.wedoogiftbackend.repository.UserRepository;
import com.allamou.wedoogiftbackend.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserBalanceResponse getTotalBalance(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Utilisateur avec l'id " + userId + " est introuvable en bdd."));
        return UserMapper.mapUserToResponse(user);
    }
}
