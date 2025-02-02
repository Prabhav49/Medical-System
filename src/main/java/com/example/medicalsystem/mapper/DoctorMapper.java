package com.example.medicalsystem.mapper;

import com.example.medicalsystem.dto.DoctorRequestDTO;
import com.example.medicalsystem.dto.DoctorResponseDTO;
import com.example.medicalsystem.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor toEntity(DoctorRequestDTO requestDTO) {
        return Doctor.builder()
                .username(requestDTO.getUsername())
                .fullName(requestDTO.getFullName())
                .password(requestDTO.getPassword())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .gender(Doctor.Gender.valueOf(requestDTO.getGender().toUpperCase()))
                .specialization(requestDTO.getSpecialization())
                .experience(requestDTO.getExperience())
                .status(Doctor.Status.ACTIVE) 
                .build();
    }

    public DoctorResponseDTO toResponse(Doctor doctor) {
        return DoctorResponseDTO.builder()
                .username(doctor.getUsername())
                .fullName(doctor.getFullName())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .gender(doctor.getGender().name())
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .status(doctor.getStatus())
                .build();
    }
}
