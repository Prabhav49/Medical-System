package com.example.medicalsystem.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.medicalsystem.dto.AdminRequestDTO;
import com.example.medicalsystem.dto.AdminResponseDTO;
import com.example.medicalsystem.entity.Admin;
import com.example.medicalsystem.mapper.AdminMapper;
import com.example.medicalsystem.repo.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    public AdminResponseDTO registerAdmin(AdminRequestDTO adminRequestDTO) {
        if (adminRepository.existsByEmail(adminRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (adminRepository.existsByPhone(adminRequestDTO.getPhone())) {
            throw new IllegalArgumentException("Phone number already in use");
        }
        Admin adminEntity = adminMapper.toEntity(adminRequestDTO);
        adminEntity.setPassword(passwordEncoder.encode(adminEntity.getPassword()));
        Admin savedAdmin = adminRepository.save(adminEntity);
        return adminMapper.toResponse(savedAdmin);
    }
    
}
