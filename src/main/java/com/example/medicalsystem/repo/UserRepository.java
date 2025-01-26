package com.example.medicalsystem.repo;

import com.example.medicalsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
