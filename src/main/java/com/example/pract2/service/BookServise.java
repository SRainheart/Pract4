package com.example.pract2.service;

import com.example.pract2.model.Book;
import com.example.pract2.model.Transport;

import java.util.List;

public interface BookServise {
    public List<Book> findAllBook();
    public Book addBook(Book student);
    public Book updateBook(Book student);
    void deleteBook(int id);
}
