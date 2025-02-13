package com.example.medicalsystem.controller;

import com.example.medicalsystem.dto.*;
import com.example.medicalsystem.service.AdminService;
import com.example.medicalsystem.service.DoctorService;
import com.example.medicalsystem.service.LabWorkerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final DoctorService doctorService;
    private final LabWorkerService labWorkerService;
    private final AdminService adminService;

    public AdminController(DoctorService doctorService, AdminService adminService, LabWorkerService labWorkerService) {
        this.doctorService = doctorService;
        this.adminService = adminService;
        this.labWorkerService = labWorkerService;
    }

    @PostMapping("/register")
    public ResponseEntity<AdminResponseDTO> registerAdmin(@Valid @RequestBody AdminRequestDTO adminRequestDTO) {
        AdminResponseDTO registeredAdmin = adminService.registerAdmin(adminRequestDTO);
        return new ResponseEntity<>(registeredAdmin, HttpStatus.CREATED);
    }

    @PostMapping("/register-doctor")
    public ResponseEntity<DoctorResponseDTO> registerDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {
        DoctorResponseDTO registeredDoctor = doctorService.registerDoctor(doctorRequestDTO);
        return new ResponseEntity<>(registeredDoctor, HttpStatus.CREATED);
    }

    @PostMapping("/register-labworker")
    public ResponseEntity<LabWorkerResponseDTO> registerLabWorker(@Valid @RequestBody LabWorkerRequestDTO labWorkerRequestDTO) {
        LabWorkerResponseDTO registeredLabWorker = labWorkerService.registerLabWorker(labWorkerRequestDTO);
        return new ResponseEntity<>(registeredLabWorker, HttpStatus.CREATED);
    }
}
