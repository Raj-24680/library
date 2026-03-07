package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book addBook(Book book) {
        return repository.save(book);
    }
    public void deleteBook(Long id) {
    repository.deleteById(id);
    }
    public Book updateBook(Long id, Book updatedBook) {
    Book book = repository.findById(id).orElseThrow();

    book.setTitle(updatedBook.getTitle());
    book.setAuthor(updatedBook.getAuthor());
    book.setQuantity(updatedBook.getQuantity());

    return repository.save(book);
}
public Book issueBook(Long id) {
    Book book = repository.findById(id).orElseThrow();

    if (book.getQuantity() > 0) {
        book.setQuantity(book.getQuantity() - 1);
        return repository.save(book);
    }

    throw new RuntimeException("Book not available");
}
public Book returnBook(Long id) {
    Book book = repository.findById(id).orElseThrow();

    book.setQuantity(book.getQuantity() + 1);

    return repository.save(book);
}

}