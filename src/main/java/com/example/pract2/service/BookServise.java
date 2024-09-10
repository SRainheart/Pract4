package com.example.pract2.service;

import com.example.pract2.model.Book;
import com.example.pract2.model.Transport;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface BookServise {
    public List<Book> findAllBook();
    public Book addBook(Book student);
    public Book updateBook(Book student);
    void deleteBook(Long id);
    public void deleteBookAll();
    public Book IsDeleteBookTrue(Book student);
    Book findBookById (Long id);
}
