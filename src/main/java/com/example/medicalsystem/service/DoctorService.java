package com.example.medicalsystem.service;

import com.example.medicalsystem.dto.DoctorUpdateDTO;
import com.example.medicalsystem.dto.DoctorResponseDTO;
import com.example.medicalsystem.dto.DoctorRequestDTO;
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

    public DoctorResponseDTO getDoctorInfo(String username) {
        Doctor doctor = doctorRepository.findByUsername(username);
        if (doctor == null) return null;
        return doctorMapper.toResponse(doctor);
    }

    public String updateDoctorDetails(Integer id, DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor == null) {
            return "Doctor not found";
        }

        StringBuilder updatedFields = new StringBuilder();

        if (doctorUpdateDTO.getFullName() != null && !doctorUpdateDTO.getFullName().equals(doctor.getFullName())) {
            doctor.setFullName(doctorUpdateDTO.getFullName());
            updatedFields.append("fullName, ");
        }
        if (doctorUpdateDTO.getPassword() != null && !doctorUpdateDTO.getPassword().equals(doctor.getPassword())) {
            doctor.setPassword(passwordEncoder.encode(doctorUpdateDTO.getPassword()));
            updatedFields.append("password, ");
        }
        if (doctorUpdateDTO.getEmail() != null && !doctorUpdateDTO.getEmail().equals(doctor.getEmail())) {
            doctor.setEmail(doctorUpdateDTO.getEmail());
            updatedFields.append("email, ");
        }
        if (doctorUpdateDTO.getPhone() != null && !doctorUpdateDTO.getPhone().equals(doctor.getPhone())) {
            doctor.setPhone(doctorUpdateDTO.getPhone());
            updatedFields.append("phone, ");
        }
        if (doctorUpdateDTO.getGender() != null && !doctorUpdateDTO.getGender().equalsIgnoreCase(doctor.getGender().name())) {
            doctor.setGender(Doctor.Gender.valueOf(doctorUpdateDTO.getGender().toUpperCase()));
            updatedFields.append("gender, ");
        }

        if (doctorUpdateDTO.getStatus() != null && !doctorUpdateDTO.getStatus().equals(doctor.getStatus().name())) {
            doctor.setStatus(Doctor.Status.valueOf(doctorUpdateDTO.getStatus().toUpperCase()));
            updatedFields.append("status, ");
        }

        if (updatedFields.length() > 0) {
            updatedFields.setLength(updatedFields.length() - 2);
            doctorRepository.save(doctor);
            return updatedFields.toString() + " changed successfully";
        }

        return "No fields were updated";
    }

    public String deactivateDoctor(Integer id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor == null) {
            return "Doctor not found";
        }

        if (doctor.getStatus() == Doctor.Status.INACTIVE) {
            return "Doctor is already deactivated";
        }

        // Deactivate the doctor
        doctor.setStatus(Doctor.Status.INACTIVE);
        doctorRepository.save(doctor);
        return "Doctor deactivated successfully";
    }

    public String activateDoctor(Integer id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor == null) {
            return "Doctor not found";
        }

        if (doctor.getStatus() == Doctor.Status.ACTIVE) {
            return "Doctor is already active";
        }

        // Activate the doctor
        doctor.setStatus(Doctor.Status.ACTIVE);
        doctorRepository.save(doctor);
        return "Doctor activated successfully";
    }
}
