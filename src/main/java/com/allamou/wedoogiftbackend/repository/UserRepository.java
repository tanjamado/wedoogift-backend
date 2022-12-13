package com.allamou.wedoogiftbackend.repository;

import com.allamou.wedoogiftbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
