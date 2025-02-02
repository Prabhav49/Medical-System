package com.example.medicalsystem.repo;

import com.example.medicalsystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmail(String email);
    Admin findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
