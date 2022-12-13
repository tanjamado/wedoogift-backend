package com.allamou.wedoogiftbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int depositId;

    @Column(name = "deposit_amount")
    private float depositAmount;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @JoinColumn(name = "user_id")
    private User user;

    private DEPOSIT_TYPE type;

    public int getDepositId() {
        return depositId;
    }

    public void setDepositId(int depositId) {
        this.depositId = depositId;
    }

    public float getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(float depositAmount) {
        this.depositAmount = depositAmount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DEPOSIT_TYPE getType() {
        return type;
    }

    public void setType(DEPOSIT_TYPE type) {
        this.type = type;
    }
}
