package com.example.medicalsystem.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.medicalsystem.auth.JwtUtil;
import com.example.medicalsystem.entity.User;
import com.example.medicalsystem.entity.Doctor;
import com.example.medicalsystem.entity.Admin;
import com.example.medicalsystem.repo.UserRepository;
import com.example.medicalsystem.repo.DoctorRepository;
import com.example.medicalsystem.repo.AdminRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;
    private final DoctorRepository doctorRepo;
    private final AdminRepository adminRepo;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepo, DoctorRepository doctorRepo, AdminRepository adminRepo) {
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.doctorRepo = doctorRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println("Received Token: " + token);

        if (token != null && jwtUtil.validateToken(token)) {
            String username = jwtUtil.extractEmail(token);  // assuming email as the unique identifier

            if (username != null) {
                User user = userRepo.findByEmail(username);
                Doctor doctor = doctorRepo.findByEmail(username);
                Admin admin = adminRepo.findByEmail(username);

                if (user != null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username, null, null);  // Assuming roles are set in User entity
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else if (doctor != null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username, null, null);  // Assuming roles are set in Doctor entity
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else if (admin != null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username, null, null);  // Assuming roles are set in Admin entity
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
