package com.allamou.wedoogiftbackend.repository;

import com.allamou.wedoogiftbackend.model.Company;
import com.allamou.wedoogiftbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByFullName(String funnName);
    List<User> findAllByCompany(Company company);

    @Query("update User u set u.giftBalance = ?2 where u.idUser = ?1")
    @Modifying
    void updateGiftBalance(int userId, double newGiftBalance);

    @Query("update User u set u.mealBalance = ?2 where u.idUser = ?1")
    @Modifying
    void updateMealBalance(int userId, double newMealBalance);


}
