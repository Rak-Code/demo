package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    // One Course -> Many Students
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Student> students;
}
