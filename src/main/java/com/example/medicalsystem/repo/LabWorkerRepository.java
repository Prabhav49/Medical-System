package com.example.medicalsystem.repo;

import com.example.medicalsystem.entity.LabWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabWorkerRepository extends JpaRepository<LabWorker, Integer> {

    LabWorker findByEmail(String email);
    LabWorker findByPhone(String phone);
    LabWorker findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
