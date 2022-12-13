package com.allamou.wedoogiftbackend.repository;

import com.allamou.wedoogiftbackend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
