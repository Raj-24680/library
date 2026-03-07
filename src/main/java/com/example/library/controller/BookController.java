package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.library.model.Book;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

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
@PutMapping("/issue/{id}")
public Book issueBook(@PathVariable Long id) {
    return service.issueBook(id);
}
@PutMapping("/return/{id}")
public Book returnBook(@PathVariable Long id) {
    return service.returnBook(id);
}
}