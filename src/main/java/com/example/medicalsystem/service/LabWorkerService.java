package com.example.medicalsystem.service;

import com.example.medicalsystem.dto.*;
import com.example.medicalsystem.entity.LabWorker;
import com.example.medicalsystem.mapper.LabWorkerMapper;
import com.example.medicalsystem.repo.LabWorkerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LabWorkerService {
    private final LabWorkerRepository labWorkerRepository;
    private final LabWorkerMapper labWorkerMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LabWorkerService(LabWorkerRepository labWorkerRepository, LabWorkerMapper labWorkerMapper) {
        this.labWorkerRepository = labWorkerRepository;
        this.labWorkerMapper = labWorkerMapper;
    }

    public LabWorkerResponseDTO registerLabWorker(LabWorkerRequestDTO labWorkerRequestDTO) {
        if (labWorkerRepository.existsByEmail(labWorkerRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (labWorkerRepository.existsByPhone(labWorkerRequestDTO.getPhone())) {
            throw new IllegalArgumentException("Phone number already in use");
        }
        LabWorker labWorker = labWorkerMapper.toEntity(labWorkerRequestDTO);
        labWorker.setPassword(passwordEncoder.encode(labWorker.getPassword()));
        LabWorker savedLabWorker = labWorkerRepository.save(labWorker);
        return labWorkerMapper.toResponse(savedLabWorker);
    }

    public LabWorkerResponseDTO getLabWorkerInfo(String username) {
        LabWorker labWorker = labWorkerRepository.findByUsername(username);
        if (labWorker == null) return null;
        return labWorkerMapper.toResponse(labWorker);
    }

    public String updateLabWorker(Integer id, LabWorkerUpdateDTO labWorkerUpdateDTO) {
        LabWorker labWorker = labWorkerRepository.findById(id).orElse(null);
        if (labWorker == null) {
            return "Lab Worker not found";
        }

        StringBuilder updatedFields = new StringBuilder();

        if (labWorkerUpdateDTO.getFullName() != null && !labWorkerUpdateDTO.getFullName().equals(labWorker.getFullName())) {
            labWorker.setFullName(labWorkerUpdateDTO.getFullName());
            updatedFields.append("fullName, ");
        }
        if (labWorkerUpdateDTO.getPassword() != null && !labWorkerUpdateDTO.getPassword().equals(labWorker.getPassword())) {
            labWorker.setPassword(passwordEncoder.encode(labWorkerUpdateDTO.getPassword()));
            updatedFields.append("password, ");
        }
        if (labWorkerUpdateDTO.getEmail() != null && !labWorkerUpdateDTO.getEmail().equals(labWorker.getEmail())) {
            labWorker.setEmail(labWorkerUpdateDTO.getEmail());
            updatedFields.append("email, ");
        }
        if (labWorkerUpdateDTO.getPhone() != null && !labWorkerUpdateDTO.getPhone().equals(labWorker.getPhone())) {
            labWorker.setPhone(labWorkerUpdateDTO.getPhone());
            updatedFields.append("phone, ");
        }
        if (labWorkerUpdateDTO.getLabDepartment() != null && !labWorkerUpdateDTO.getLabDepartment().equals(labWorker.getLabDepartment())) {
            labWorker.setLabDepartment(labWorkerUpdateDTO.getLabDepartment());
            updatedFields.append("labDepartment, ");
        }
        if (labWorkerUpdateDTO.getStatus() != null && !labWorkerUpdateDTO.getStatus().equals(labWorker.getStatus().name())) {
            labWorker.setStatus(LabWorker.Status.valueOf(labWorkerUpdateDTO.getStatus().toUpperCase()));
            updatedFields.append("status, ");
        }

        if (updatedFields.length() > 0) {
            updatedFields.setLength(updatedFields.length() - 2);
            labWorkerRepository.save(labWorker);
            return updatedFields.toString() + " changed successfully";
        }

        return "No fields were updated";
    }


    public String deactivateLabWorker(Integer id) {
        LabWorker labWorker = labWorkerRepository.findById(id).orElse(null);
        if (labWorker == null) {
            return "Lab Worker not found";
        }

        if (labWorker.getStatus() == LabWorker.Status.INACTIVE) {
            return "Lab Worker is already deactivated";
        }

        labWorker.setStatus(LabWorker.Status.INACTIVE);
        labWorkerRepository.save(labWorker);
        return "Lab Worker deactivated successfully";
    }

    public String activateLabWorker(Integer id) {
        LabWorker labWorker = labWorkerRepository.findById(id).orElse(null);
        if (labWorker == null) {
            return "Lab Worker not found";
        }

        if (labWorker.getStatus() == LabWorker.Status.ACTIVE) {
            return "Lab Worker is already active";
        }

        labWorker.setStatus(LabWorker.Status.ACTIVE);
        labWorkerRepository.save(labWorker);
        return "Lab Worker activated successfully";
    }
}
