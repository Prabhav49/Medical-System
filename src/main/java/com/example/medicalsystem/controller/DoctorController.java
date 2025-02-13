package com.example.medicalsystem.controller;

import com.example.medicalsystem.dto.DoctorResponseDTO;
import com.example.medicalsystem.dto.DoctorUpdateDTO;
import com.example.medicalsystem.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
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
