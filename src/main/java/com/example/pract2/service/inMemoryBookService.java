package com.example.pract2.service;

import com.example.pract2.model.Book;
import com.example.pract2.repository.InMemoryBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class inMemoryBookService implements BookServise{
    private final InMemoryBookRepository studentRepository;

    public inMemoryBookService(InMemoryBookRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Book> findAllBook() {
        return studentRepository.findAllBook();
    }

    @Override
    public Book addBook(Book student) {
        return studentRepository.addBook(student);
    }

    @Override
    public Book updateBook(Book student) {
        return studentRepository.updateBook(student);
    }

    @Override
    public void deleteBook(int id) {
        studentRepository.deleteBook(id);
    }
}
