package com.allamou.wedoogiftbackend.dto;

public class UserBalanceResponse {
    private String fullName;
    private double giftBalance;
    private double mealBalance;
    private double totalBalance;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getGiftBalance() {
        return giftBalance;
    }

    public void setGiftBalance(double giftBalance) {
        this.giftBalance = giftBalance;
    }

    public double getMealBalance() {
        return mealBalance;
    }

    public void setMealBalance(double mealBalance) {
        this.mealBalance = mealBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }
}
