package com.example.pract2.repository;

import com.example.pract2.model.StudentModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


///
// файл не используеться
///

@Repository
//Репозиторий отвечает за хранение и управление данными студентов в памяти. Он предоставляет методы для выполнения операций(обычные CRUD действия с данными)

public class InMemoryStudentRepository {
    private final List<StudentModel> STUDENT = new ArrayList<>();
    public List<StudentModel> findAllStudents() {//Вывод всех студентов
        return STUDENT;
    }
    public StudentModel findStudentById(Long id) {
        return STUDENT
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public StudentModel addStudent(StudentModel student){// Добавление студента
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

    public void deleteStudent(Long id){ // Удаление студента по id
        var student = findStudentById(id);
        if (student != null){
            STUDENT.remove(student);
        }
    }

    public List<StudentModel> findStudentByName(String lastname){//поиск по id
        return STUDENT.stream()
                .filter(studentModel -> studentModel.getLastName().equals(lastname))
                .collect(Collectors.toList());
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

    public List<StudentModel> filterStudentNo18(){
        return STUDENT.stream().filter(studentModel -> studentModel.getAge() < 18).collect(Collectors.toList());
    }

    public List<StudentModel> filterStudentAlfavit(){
        Collections.sort(STUDENT, Comparator.comparing(StudentModel::getLastName));
        return STUDENT;
    }
}
