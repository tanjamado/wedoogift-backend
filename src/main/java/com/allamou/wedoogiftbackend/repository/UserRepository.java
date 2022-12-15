package com.allamou.wedoogiftbackend.repository;

import com.allamou.wedoogiftbackend.model.Company;
import com.allamou.wedoogiftbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByCompany(Company company);
    List<User> findAllByCompany(Company company);


}
