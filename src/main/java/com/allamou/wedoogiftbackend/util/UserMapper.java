package com.allamou.wedoogiftbackend.util;

import com.allamou.wedoogiftbackend.dto.UserBalanceResponse;
import com.allamou.wedoogiftbackend.dto.UserDto;
import com.allamou.wedoogiftbackend.model.User;

public class UserMapper {

    public static UserBalanceResponse mapUserToResponse(User user) {

        UserBalanceResponse userBalanceResponse = new UserBalanceResponse();
        userBalanceResponse.setFullName(user.getFullName());
        userBalanceResponse.setGiftBalance(user.getGiftBalance());
        userBalanceResponse.setMealBalance(user.getMealBalance());
        userBalanceResponse.setTotalBalance(user.getTotalBalance());

        return userBalanceResponse;
    }

    public static UserDto mapUserToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setUserId(user.getIdUser());
        userDto.setFullName(user.getFullName());
        userDto.setGiftBalance(user.getGiftBalance());
        userDto.setMealBalance(user.getMealBalance());
        userDto.setTotalBalance(user.getTotalBalance());

        return userDto;
    }
}
