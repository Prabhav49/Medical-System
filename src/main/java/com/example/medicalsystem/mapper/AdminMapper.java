package com.example.medicalsystem.mapper;

import com.example.medicalsystem.dto.AdminRequestDTO;
import com.example.medicalsystem.dto.AdminResponseDTO;
import com.example.medicalsystem.dto.AdminUpdateDTO;
import com.example.medicalsystem.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin toEntity(AdminRequestDTO requestDTO) {
        return Admin.builder()
                .fullName(requestDTO.getFullName())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .password(requestDTO.getPassword())
                .status(Admin.Status.valueOf(requestDTO.getStatus().toUpperCase()))
                .build();
    }

    public AdminResponseDTO toResponse(Admin admin) {
        return AdminResponseDTO.builder()
                .fullName(admin.getFullName())
                .email(admin.getEmail())
                .phone(admin.getPhone())
                .status(admin.getStatus().name())
                .build();
    }

    public Admin toUpdateEntity(AdminUpdateDTO updateDTO, Admin existingAdmin) {
        existingAdmin.setFullName(updateDTO.getFullName());
        existingAdmin.setEmail(updateDTO.getEmail());
        existingAdmin.setPhone(updateDTO.getPhone());
        existingAdmin.setStatus(Admin.Status.valueOf(updateDTO.getStatus().toUpperCase()));
        return existingAdmin;
    }
}
