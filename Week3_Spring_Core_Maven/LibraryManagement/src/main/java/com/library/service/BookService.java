package com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Book;
import com.library.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    private Book book;

    @Autowired
    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;

    }

    public void issueBook() {

        System.out.println("Book Service Started");

        bookRepository.getBook();

        book.displayBook();

        System.out.println("Book Issued Successfully");

    }

}