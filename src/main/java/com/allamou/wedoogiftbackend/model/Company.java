package com.allamou.wedoogiftbackend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    private String name;
    private float balance;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<User> getUsers() {
        return users;
    }

    //Gestion correcte de l'autre côté du OneToMany
    public void addUser(User user, boolean setOnOtherSide) {
        if(users.contains(user)) return;
        else getUsers().add(user);
        if(setOnOtherSide) user.setCompany(this);
    }

    //Gestion correcte de l'autre côté du OneToMany
    public void removeUser(User user) {
        if(!users.contains(user)) return;
        users.remove(user);
        user.setCompany(null);
    }
}
