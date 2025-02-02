package com.example.medicalsystem.repo;

import com.example.medicalsystem.entity.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;



public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Doctor findByEmail(String email);
    Doctor findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);


}
