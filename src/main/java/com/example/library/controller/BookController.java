package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.library.model.Book;
import com.example.library.model.IssueRecord;
import com.example.library.repository.IssueRecordRepository;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;
    @Autowired
    private IssueRecordRepository issueRepository;
    @GetMapping
    public List<Book> getBooks() {
        return service.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
    return service.updateBook(id, book);
}
@PutMapping("/issue/{id}/{usn}")
public Book issueBook(@PathVariable Long id, @PathVariable String usn) {
    return service.issueBook(id, usn);
}
@PutMapping("/return/{recordId}")
public Book returnBook(@PathVariable Long recordId) {
    return service.returnBook(recordId);
}
@GetMapping("/issued")
public List<IssueRecord> getIssuedBooks() {
    return issueRepository.findAll();
}
}