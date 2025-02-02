package com.example.medicalsystem.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalsystem.dto.LoginRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth/")
public class AuthController {
        private final AuthService authService;

        @PostMapping("/user/login")
        public ResponseEntity<String> userLogin(@RequestBody LoginRequest request) {
            String token = authService.userLogin(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(token);
        }

        @PostMapping("/admin/login")
        public ResponseEntity<String> adminLogin(@RequestBody LoginRequest request) {
            String token = authService.adminLogin(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(token);
        }
        
        @PostMapping("/doctor/login")
        public ResponseEntity<String> doctorLogin(@RequestBody LoginRequest request) {
            String token = authService.doctorLogin(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(token);
        }
}
