package com.example.pract2.repository;

import com.example.pract2.model.StudentModel;
import com.example.pract2.model.Transport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Repository
public class InMemoryTransportRepository {
    private final List<Transport> STUDENT = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(0);// Генерация уникального ID

    public List<Transport> findAllTransport() {//Вывод всех студентов
        return STUDENT;
    }

    public Transport addTransport(Transport student){// Добавление студента
        student.setId(idCounter.getAndIncrement());// Установка уникального ID
        STUDENT.add(student);
        return student;
    }

    public Transport updateTransport(Transport student){ // изменение студента
        for (int i = 0; i < STUDENT.size(); i++){
            if (STUDENT.get(i).getId() == student.getId()){
                STUDENT.set(i, student);
                return student;
            }
        }
        return null;// Студент не найден
    }

    public void deleteTransport(int id){ // Удаление студента по id
        STUDENT.removeIf(transport -> transport.getId() == id);
    }

//    public Transport findStudentById(int id){//поиск по id
//        return STUDENT.stream()
//                .filter(studentModel -> studentModel.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public void deleteStudentAll(){//очищение всего списка, по факту множественное удалени
//        STUDENT.clear();
//    }
//
//    public Transport IsDeleteTrue(Transport student){//логическое удаление
//        for (int i = 0; i<STUDENT.size(); i++){
//            if (STUDENT.get(i).getId() == student.getId()){
//                student.setDelete(true);
//                STUDENT.set(i, student);
//                return student;
//            }
//        }
//        return null;
//    }
//
//    public List<Transport> filterStudentDelTwo(){//фильтрация
//        return STUDENT.stream().filter(transport -> transport.getType() == "Автомобиль").collect(Collectors.toList());
//    }
}
