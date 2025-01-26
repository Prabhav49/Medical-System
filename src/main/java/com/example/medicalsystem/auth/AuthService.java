package com.example.medicalsystem.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.medicalsystem.entity.User;
import com.example.medicalsystem.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(String email, String password){
        User user = userRepo.findByEmail(email);
        if(user != null &&passwordEncoder.matches(password, user.getPassword())){
            return jwtUtil.generateToken(email);
        }
        else{
            return "Invalid email or password";
        }
    }
}
