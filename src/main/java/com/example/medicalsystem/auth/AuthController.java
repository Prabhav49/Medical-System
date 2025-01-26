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

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody LoginRequest request) {
            String token = authService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(token);
        }
        
    
}
