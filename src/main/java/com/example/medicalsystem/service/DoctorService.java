package com.example.medicalsystem.service;

import com.example.medicalsystem.dto.DoctorRequestDTO;
import com.example.medicalsystem.dto.DoctorResponseDTO;
import com.example.medicalsystem.entity.Doctor;
import com.example.medicalsystem.mapper.DoctorMapper;
import com.example.medicalsystem.repo.DoctorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public DoctorResponseDTO registerDoctor(DoctorRequestDTO doctorRequestDTO) {
        if (doctorRepository.existsByEmail(doctorRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (doctorRepository.existsByPhone(doctorRequestDTO.getPhone())) {
            throw new IllegalArgumentException("Phone number already in use");
        }
        Doctor doctorEntity = doctorMapper.toEntity(doctorRequestDTO);
        doctorEntity.setPassword(passwordEncoder.encode(doctorEntity.getPassword()));
        Doctor savedDoctor = doctorRepository.save(doctorEntity);
        return doctorMapper.toResponse(savedDoctor);
    }
}
