package com.example.medicalsystem.repo;

import com.example.medicalsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;




public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    User findByUsername(String username);
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
