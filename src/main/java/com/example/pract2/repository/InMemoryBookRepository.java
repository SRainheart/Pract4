package com.example.pract2.repository;

import com.example.pract2.model.Book;
import com.example.pract2.model.Transport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryBookRepository {
    private final List<Book> STUDENT = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(0);// Генерация уникального ID

    public List<Book> findAllBook() {//Вывод всех студентов
        return STUDENT;
    }

    public Book addBook(Book student){// Добавление студента
        student.setId(idCounter.getAndIncrement());// Установка уникального ID
        STUDENT.add(student);
        return student;
    }

    public Book updateBook(Book student){ // изменение студента
        for (int i = 0; i < STUDENT.size(); i++){
            if (STUDENT.get(i).getId() == student.getId()){
                STUDENT.set(i, student);
                return student;
            }
        }
        return null;// Студент не найден
    }

    public void deleteBook(int id){ // Удаление студента по id
        STUDENT.removeIf(book -> book.getId() == id);
    }

}
