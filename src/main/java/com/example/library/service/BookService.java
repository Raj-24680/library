package com.example.library.service;
import com.example.library.model.IssueRecord;
import com.example.library.repository.IssueRecordRepository;
import java.time.LocalDateTime;
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
    @Autowired
    private IssueRecordRepository issueRepository;

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
public Book issueBook(Long id, String usn) {

    Book book = repository.findById(id).orElseThrow();

    if (book.getQuantity() <= 0) {
        throw new RuntimeException("Book not available");
    }

    book.setQuantity(book.getQuantity() - 1);

    IssueRecord record = new IssueRecord(usn, book);
    issueRepository.save(record);

    return repository.save(book);
}
public Book returnBook(Long recordId) {

    IssueRecord record = issueRepository.findById(recordId).orElseThrow();

    if (record.isReturned()) {
        throw new RuntimeException("Book already returned");
    }

    Book book = record.getBook();

    if (book.getQuantity() >= book.getTotalCopies()) {
        throw new RuntimeException("All books already returned");
    }

    book.setQuantity(book.getQuantity() + 1);

    record.setReturned(true);
    record.setReturnDate(LocalDateTime.now());

    issueRepository.save(record);

    return repository.save(book);
}
}