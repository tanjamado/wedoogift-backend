package com.allamou.wedoogiftbackend.controller;

import com.allamou.wedoogiftbackend.dto.UserBalanceResponse;
import com.allamou.wedoogiftbackend.dto.UserDto;
import com.allamou.wedoogiftbackend.service.CompanyService;
import com.allamou.wedoogiftbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final CompanyService companyService;

    @Autowired
    public UserController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/balance")
    public ResponseEntity<UserBalanceResponse> getTotalBalance(@RequestParam("user_id") int userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getTotalBalance(userId));
    }
}
