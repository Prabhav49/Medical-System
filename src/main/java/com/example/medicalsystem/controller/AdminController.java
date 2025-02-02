package com.example.medicalsystem.controller;

import com.example.medicalsystem.dto.AdminRequestDTO;
import com.example.medicalsystem.dto.AdminResponseDTO;
import com.example.medicalsystem.dto.DoctorRequestDTO;
import com.example.medicalsystem.dto.DoctorResponseDTO;
import com.example.medicalsystem.dto.DoctorUpdateDTO;
import com.example.medicalsystem.service.AdminService;
import com.example.medicalsystem.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final DoctorService doctorService;
    private final AdminService adminService;

    public AdminController(DoctorService doctorService, AdminService adminService) {
        this.doctorService = doctorService;
        this.adminService = adminService;
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

    @GetMapping("/getDoctorByUsername/{username}")
    public ResponseEntity<DoctorResponseDTO> getDoctorByUsername(@PathVariable String username) {
        DoctorResponseDTO doctor = doctorService.getDoctorInfo(username);
        if (doctor == null) return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok(doctor);
    }

    @PatchMapping("/update-doctor/{id}")
    public String updateDoctorDetails(@PathVariable Integer id, @Valid @RequestBody DoctorUpdateDTO doctorUpdateDTO) {
        return doctorService.updateDoctorDetails(id, doctorUpdateDTO);
    }

    @PutMapping("/deactivate-doctor/{id}")
    public String deactivateDoctor(@PathVariable Integer id) {
        return doctorService.deactivateDoctor(id);
    }

    @PutMapping("/activate-doctor/{id}")
    public String activateDoctor(@PathVariable Integer id) {
        return doctorService.activateDoctor(id);
    }
}
