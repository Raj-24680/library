package com.example.library.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String usn;

    public Student() {}

    public Student(String name, String usn) {
        this.name = name;
        this.usn = usn;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsn() {
        return usn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }
}