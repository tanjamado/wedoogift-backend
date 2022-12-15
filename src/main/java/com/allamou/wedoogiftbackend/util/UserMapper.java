package com.allamou.wedoogiftbackend.util;

import com.allamou.wedoogiftbackend.dto.UserBalanceResponse;
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
}
