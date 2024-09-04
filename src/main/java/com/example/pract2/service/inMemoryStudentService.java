package com.example.pract2.service;

import com.example.pract2.model.StudentModel;
import com.example.pract2.repository.InMemoryStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//Сервисный слой отвечает за бизнес-логику приложения. Он использует репозиторий для выполнения операций с данными и может включать дополнительные проверки или преобразования данных
//так же мы тут можем настроить инкапсуляцию
//А если простыми словами тут происходит разделенние запросов от контроллера к сервису

@Service
public class inMemoryStudentService implements StudentServise {
    private final InMemoryStudentRepository studentRepository;

    public inMemoryStudentService(InMemoryStudentRepository studentRepository) { // Конструктор
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentModel> findAllStudent() { // Вывод всех студентов
        return studentRepository.findAllStudents();
    }

    @Override
    public StudentModel addStudent(StudentModel student) { // Добавление студента
        return studentRepository.addStudent(student);
    }

    @Override
    public StudentModel updateStudent(StudentModel student) { // Изменение студента
        return studentRepository.updateStudent(student);
    }

    @Override
    public void deleteStudent(int id) { // Удаление студента
        studentRepository.deleteStudent(id);
    }

    @Override
    public StudentModel findStudentById(int id) { // поиск студента по id
        return studentRepository.findStudentById(id);
    }
    @Override
    public void deleteStudentAll() {// удаление всех сдудентов
        studentRepository.deleteStudentAll();
    }

    @Override
    public StudentModel IsDeleteTrue(StudentModel student) {//логическое удаление
        return studentRepository.IsDeleteTrue(student);
    }

    @Override
    public List<StudentModel> filterStudentsDelTwo() {//фильтрация
        return studentRepository.filterStudentDelTwo();
    }

}
