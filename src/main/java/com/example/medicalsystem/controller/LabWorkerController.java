package com.example.medicalsystem.controller;

import com.example.medicalsystem.dto.LabWorkerRequestDTO;
import com.example.medicalsystem.dto.LabWorkerResponseDTO;
import com.example.medicalsystem.dto.LabWorkerUpdateDTO;
import com.example.medicalsystem.service.LabWorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab-workers")
public class LabWorkerController {

    private final LabWorkerService labWorkerService;

    public LabWorkerController(LabWorkerService labWorkerService) {
        this.labWorkerService = labWorkerService;
    }

    @GetMapping("/getLabWorkerInfo/{username}")
    public ResponseEntity<LabWorkerResponseDTO> getLabWorkerInfo(@PathVariable String username) {
        LabWorkerResponseDTO response = labWorkerService.getLabWorkerInfo(username);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLabWorker(@PathVariable Integer id, @RequestBody LabWorkerUpdateDTO labWorkerUpdateDTO) {
        return ResponseEntity.ok(labWorkerService.updateLabWorker(id, labWorkerUpdateDTO));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateLabWorker(@PathVariable Integer id) {
        return ResponseEntity.ok(labWorkerService.deactivateLabWorker(id));
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<String> activateLabWorker(@PathVariable Integer id) {
        return ResponseEntity.ok(labWorkerService.activateLabWorker(id));
    }
}
