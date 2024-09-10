package com.example.pract2.service;

import com.example.pract2.model.Book;
import com.example.pract2.repository.BookRepository;
import com.example.pract2.repository.InMemoryBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class inMemoryBookService implements BookServise{
    private final BookRepository studentRepository;
    public inMemoryBookService(BookRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public List<Book> findAllBook() {
        return studentRepository.findAll();
    }
    @Override
    public Book addBook(Book student) {
        return studentRepository.save(student);
    }
    @Override
    public Book updateBook(Book student) {
        return studentRepository.save(student);
    }
    @Override
    public void deleteBook(Long id) {
        studentRepository.deleteById(id);
    }
    @Override
    public void deleteBookAll() {
        studentRepository.deleteAll();
    }
    @Override
    public Book IsDeleteBookTrue(Book student) {
        return studentRepository.save(student);
    }
    @Override
    public Book findBookById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
