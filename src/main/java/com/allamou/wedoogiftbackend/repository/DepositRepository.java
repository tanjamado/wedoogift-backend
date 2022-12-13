package com.allamou.wedoogiftbackend.repository;

import com.allamou.wedoogiftbackend.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {
}
