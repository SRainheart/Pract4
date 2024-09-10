package com.example.pract2.service;

import com.example.pract2.model.StudentModel;
import com.example.pract2.repository.InMemoryStudentRepository;
import com.example.pract2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//Сервисный слой отвечает за бизнес-логику приложения. Он использует репозиторий для выполнения операций с данными и может включать дополнительные проверки или преобразования данных
//так же мы тут можем настроить инкапсуляцию
//А если простыми словами тут происходит разделенние запросов от контроллера к сервису

@Service
public class inMemoryStudentService implements StudentServise {
    private final StudentRepository studentRepository;

    public inMemoryStudentService(StudentRepository studentRepository) { // Конструктор
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentModel> findAllStudent() { // Вывод всех студентов
        return studentRepository.findAll();
    }

    @Override
    public StudentModel addStudent(StudentModel student) { // Добавление студента
        return studentRepository.save(student);
    }

    @Override
    public StudentModel updateStudent(StudentModel student) { // Изменение студента
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) { // Удаление студента
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteStudentAll() {
        studentRepository.deleteAll();// удаление всех студентов
    }

    @Override
    public StudentModel IsDeleteTrue(StudentModel student) {//логическое удаление
        return studentRepository.save(student);
    }
}