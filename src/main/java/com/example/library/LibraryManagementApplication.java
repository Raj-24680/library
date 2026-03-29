package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import com.example.library.repository.BookRepository;
import com.example.library.repository.IssueRecordRepository;
import com.example.library.model.Book;

@SpringBootApplication
public class LibraryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

	// ✅ ADD THIS PART
	@Bean
CommandLineRunner initBooks(BookRepository repo, IssueRecordRepository issueRepo) {
    return args -> {

        issueRepo.deleteAll();   // ✅ delete child first
        repo.deleteAll();        // ✅ then parent

        repo.save(new Book("Java Basics", "James Gosling", 5));
        repo.save(new Book("Spring Boot", "Pivotal", 3));
        repo.save(new Book("Data Structures", "Mark Allen Weiss", 4));
    };
}
}