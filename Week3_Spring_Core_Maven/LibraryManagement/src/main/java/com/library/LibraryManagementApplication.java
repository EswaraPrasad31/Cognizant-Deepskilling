package com.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.library.service.BookService;

@SpringBootApplication
public class LibraryManagementApplication implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {

        SpringApplication.run(LibraryManagementApplication.class, args);

    }

    @Override
    public void run(String... args) {

        bookService.issueBook();

    }

}