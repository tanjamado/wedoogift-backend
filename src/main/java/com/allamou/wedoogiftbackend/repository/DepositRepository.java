package com.allamou.wedoogiftbackend.repository;

import com.allamou.wedoogiftbackend.model.Deposit;
import com.allamou.wedoogiftbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    Optional<Deposit> findByUser(User user);
    List<Deposit> findAllByUser(User user);


}
