package com.allamou.wedoogiftbackend.service;

import com.allamou.wedoogiftbackend.dto.UserBalanceResponse;
import com.allamou.wedoogiftbackend.dto.UserDto;
import com.allamou.wedoogiftbackend.exception.UserNotFoundException;
import com.allamou.wedoogiftbackend.model.User;
import com.allamou.wedoogiftbackend.repository.UserRepository;
import com.allamou.wedoogiftbackend.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::mapUserToDto)
                .collect(Collectors.toList());
    }

    public UserBalanceResponse getTotalBalance(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Utilisateur avec l'id " + userId + " est introuvable en bdd."));
        return UserMapper.mapUserToResponse(user);
    }
}
