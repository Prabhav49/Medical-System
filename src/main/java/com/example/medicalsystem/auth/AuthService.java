package com.example.medicalsystem.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.medicalsystem.entity.User;
import com.example.medicalsystem.entity.Doctor;
import com.example.medicalsystem.entity.Admin;
import com.example.medicalsystem.repo.AdminRepository;
import com.example.medicalsystem.repo.UserRepository;
import com.example.medicalsystem.repo.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final DoctorRepository doctorRepo;
    private final AdminRepository adminRepo;

    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String userLogin(String email, String password){
        User user = userRepo.findByEmail(email);
        if(user != null &&passwordEncoder.matches(password, user.getPassword())){
            return jwtUtil.generateToken(email);
        }
        else{
            return "Invalid email or password";
        }
    }
    public String doctorLogin(String email, String password){
        Doctor doctor = doctorRepo.findByEmail(email);
        if(doctor != null &&passwordEncoder.matches(password, doctor.getPassword())){
            return jwtUtil.generateToken(email);
        }
        else{
            return "Invalid email or password";
        }
    }
    public String adminLogin(String email, String password){
        Admin admin = adminRepo.findByEmail(email);
        if(admin != null &&passwordEncoder.matches(password, admin.getPassword())){
            return jwtUtil.generateToken(email);
        }
        else{
            return "Invalid email or password";
        }
    }
}
