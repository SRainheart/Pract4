package com.example.pract2.repository;

import com.example.pract2.model.Book;
import com.example.pract2.model.StudentModel;
import com.example.pract2.model.Transport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository {
    private final List<Book> STUDENT = new ArrayList<>();
    public List<Book> findAllBook() {//Вывод всех студентов
        return STUDENT;
    }
    public Book findBookById(Long id){
        return STUDENT
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Book addBook(Book student){// Добавление студента
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

    public void deleteBook(Long id){ // Удаление студента по id
        var book = findBookById(id);
        if(book != null){
            STUDENT.remove(book);
        }
    }

    public List<Book> findStudentByBook(String lastname){//поиск по id
        return STUDENT.stream()
                .filter(book -> book.getName().equals(lastname))
                .collect(Collectors.toList());
    }

    public void deleteBookAll(){//очищение всего списка, по факту множественное удалени
        STUDENT.clear();
    }

    public Book IsDeleteBookTrue(Book student){//логическое удаление
        for (int i = 0; i<STUDENT.size(); i++){
            if (STUDENT.get(i).getId() == student.getId()){
                student.setDelete(true);
                STUDENT.set(i, student);
                return student;
            }
        }
        return null;
    }

    public List<Book> filterStudentDelTwo(){//фильтрация
        return STUDENT.stream().filter(book -> book.getPrice() >= 1000).collect(Collectors.toList());
    }

    public List<Book> filterStudentNo18(){
        return STUDENT.stream().filter(book -> book.getPrice() < 1000).collect(Collectors.toList());
    }

    public List<Book> filterStudentAlfavit(){
        Collections.sort(STUDENT, Comparator.comparing(Book::getAuthor));
        return STUDENT;
    }
}
