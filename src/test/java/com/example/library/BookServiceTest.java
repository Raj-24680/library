package com.example.library;

import com.example.library.model.Book;
import com.example.library.service.BookService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService service;

    // Test Add Book
    @Test
    void testAddBook() {

        Book book = new Book("JUnit Book","Test Author",5);

        Book saved = service.addBook(book);

        assertNotNull(saved);
        assertEquals("JUnit Book", saved.getTitle());
    }

    // Test View Books
    @Test
    void testGetAllBooks(){

        List<Book> books = service.getAllBooks();

        assertNotNull(books);
    }

    // Test Update Book
    @Test
    void testUpdateBook(){

        Book book = new Book("Update Test","Author",3);
        Book saved = service.addBook(book);

        Book updated = new Book("Updated Title","New Author",10);

        Book result = service.updateBook(saved.getId(), updated);

        assertEquals("Updated Title", result.getTitle());
        assertEquals(10, result.getQuantity());
    }

    // Test Delete Book
    @Test
    void testDeleteBook(){

        Book book = new Book("Delete Test","Author",2);
        Book saved = service.addBook(book);

        service.deleteBook(saved.getId());

        List<Book> books = service.getAllBooks();

        boolean exists = books.stream()
                .anyMatch(b -> b.getId().equals(saved.getId()));

        assertFalse(exists);
    }

    // Test Issue Book
    @Test
    void testIssueBook(){

        Book book = new Book("Issue Test","Author",5);
        Book saved = service.addBook(book);

        Book issued = service.issueBook(saved.getId());

        assertEquals(4, issued.getQuantity());
    }

    // Test Return Book
    @Test
    void testReturnBook(){

        Book book = new Book("Return Test","Author",2);
        Book saved = service.addBook(book);

        service.issueBook(saved.getId());

        Book returned = service.returnBook(saved.getId());

        assertEquals(2, returned.getQuantity());
    }
}