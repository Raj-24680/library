package com.example.library.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class IssueRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentUsn;

    private LocalDateTime issueDate;

    private LocalDateTime returnDate;

    private boolean returned;

    @ManyToOne
    private Book book;

    public IssueRecord() {}

    public IssueRecord(String studentUsn, Book book) {
        this.studentUsn = studentUsn;
        this.book = book;
        this.issueDate = LocalDateTime.now();
        this.returned = false;
    }

    public Long getId() {
        return id;
    }

    public String getStudentUsn() {
        return studentUsn;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public Book getBook() {
        return book;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}