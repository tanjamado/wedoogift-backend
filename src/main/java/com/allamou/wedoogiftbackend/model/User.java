package com.allamou.wedoogiftbackend.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gift_balance")
    private double giftBalance;

    @Column(name = "meal_balance")
    private double mealBalance;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Deposit> userDeposits =  new ArrayList<>();

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public double getTotalBalance() {
        return getGiftBalance() + getMealBalance();
    }

    public List<Deposit> getUserDeposits() {
        return userDeposits;
    }

    public void setUserDeposits(List<Deposit> userDeposits) {
        this.userDeposits = userDeposits;
    }
}
