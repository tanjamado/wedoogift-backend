package com.allamou.wedoogiftbackend.model;

import jakarta.persistence.*;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gift_balance")
    private float giftBalance;
    @Column(name = "meal_balance")
    private float mealBalance;

    private Deposit deposit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getGiftBalance() {
        return giftBalance;
    }

    public void setGiftBalance(float giftBalance) {
        this.giftBalance = giftBalance;
    }

    public float getMealBalance() {
        return mealBalance;
    }

    public void setMealBalance(float mealBalance) {
        this.mealBalance = mealBalance;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
