package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.library.model.Student;
import com.example.library.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @PostMapping("/login")
    public Student login(@RequestBody Student student) {
        return repository.save(student);
    }
}