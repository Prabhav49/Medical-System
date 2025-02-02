package com.example.medicalsystem.mapper;

import com.example.medicalsystem.dto.LabWorkerRequestDTO;
import com.example.medicalsystem.dto.LabWorkerResponseDTO;
import com.example.medicalsystem.entity.LabWorker;
import org.springframework.stereotype.Component;

@Component
public class LabWorkerMapper {

    public LabWorker toEntity(LabWorkerRequestDTO requestDTO) {
        return LabWorker.builder()
                .fullName(requestDTO.getFullName())
                .password(requestDTO.getPassword())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .labDepartment(requestDTO.getLabDepartment())
                .status(LabWorker.Status.ACTIVE) // Default status
                .build();
    }

    public LabWorkerResponseDTO toResponse(LabWorker labWorker) {
        return LabWorkerResponseDTO.builder()
                .fullName(labWorker.getFullName())
                .email(labWorker.getEmail())
                .phone(labWorker.getPhone())
                .labDepartment(labWorker.getLabDepartment())
                .status(labWorker.getStatus())
                .build();
    }
}
