package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    // Many Students -> One Course (Foreign Key)
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
