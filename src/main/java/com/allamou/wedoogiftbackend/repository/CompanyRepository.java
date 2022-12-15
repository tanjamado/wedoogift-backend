package com.allamou.wedoogiftbackend.repository;

import com.allamou.wedoogiftbackend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByName(String companyName);

    @Query("update Company c set c.balance = ?2 where c.companyId = ?1")
    void updateBalance(int companyId, double newBalance);


}
