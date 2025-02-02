package com.example.medicalsystem.controller;

import com.example.medicalsystem.dto.DoctorRequestDTO;
import com.example.medicalsystem.dto.DoctorResponseDTO;
import com.example.medicalsystem.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register-doctor")
    public ResponseEntity<DoctorResponseDTO> registerDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {
        DoctorResponseDTO registeredDoctor = doctorService.registerDoctor(doctorRequestDTO);
        return new ResponseEntity<>(registeredDoctor, HttpStatus.CREATED);
    }
}
