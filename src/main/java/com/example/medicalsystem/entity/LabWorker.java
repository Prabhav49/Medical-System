package com.example.medicalsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lab_workers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabWorker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String labDepartment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
