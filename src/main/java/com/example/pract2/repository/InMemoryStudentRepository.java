package com.example.pract2.repository;

import com.example.pract2.model.StudentModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
//Репозиторий отвечает за хранение и управление данными студентов в памяти. Он предоставляет методы для выполнения операций(обычные CRUD действия с данными)

public class InMemoryStudentRepository {
    private final List<StudentModel> STUDENT = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(0);// Генерация уникального ID

    public List<StudentModel> findAllStudents() {//Вывод всех студентов
        return STUDENT;
    }

    public StudentModel addStudent(StudentModel student){// Добавление студента
        student.setId(idCounter.getAndIncrement());// Установка уникального ID
        STUDENT.add(student);
        return student;
    }

    public StudentModel updateStudent(StudentModel student){ // изменение студента
        for (int i = 0; i < STUDENT.size(); i++){
            if (STUDENT.get(i).getId() == student.getId()){
                STUDENT.set(i, student);
                return student;
            }
        }
        return null;// Студент не найден
    }

    public void deleteStudent(int id){ // Удаление студента по id
        STUDENT.removeIf(studentModel -> studentModel.getId() == id);
    }

    public StudentModel findStudentById(int id){//поиск по id
        return STUDENT.stream()
                .filter(studentModel -> studentModel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteStudentAll(){//очищение всего списка, по факту множественное удалени
        STUDENT.clear();
    }

    public StudentModel IsDeleteTrue(StudentModel student){//логическое удаление
        for (int i = 0; i<STUDENT.size(); i++){
            if (STUDENT.get(i).getId() == student.getId()){
                student.setDelete(true);
                STUDENT.set(i, student);
                return student;
            }
        }
        return null;
    }

    public List<StudentModel> filterStudentDelTwo(){//фильтрация
        return STUDENT.stream().filter(studentModel -> studentModel.getAge() >= 18).collect(Collectors.toList());
    }
}
