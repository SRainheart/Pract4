package com.example.pract2.repository;
import com.example.pract2.model.Transport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Repository
public class InMemoryTransportRepository {
    private final List<Transport> STUDENT = new ArrayList<>();

    public List<Transport> findAllTransport() {//Вывод всех студентов
        return STUDENT;
    }
    public Transport findStudentById(Long id) {
        return STUDENT
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Transport addTransport(Transport student){// Добавление студента
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

    public void deleteTransport(Long id){ // Удаление студента по id
        var student = findStudentById(id);
        if (student != null){
            STUDENT.remove(student);
        }
    }


    public List<Transport> findTransport(String lastname){//поиск по id
        return STUDENT.stream()
                .filter(transport -> transport.getName().equals(lastname))
                .collect(Collectors.toList());
    }

    public void deleteTransportAll(){//очищение всего списка, по факту множественное удалени
        STUDENT.clear();
    }

    public Transport IsDeleteTransportTrue(Transport student){//логическое удаление
        for (int i = 0; i<STUDENT.size(); i++){
            if (STUDENT.get(i).getId() == student.getId()){
                student.setDelete(true);
                STUDENT.set(i, student);
                return student;
            }
        }
        return null;
    }

    public List<Transport> filterTransportDelTwo(){//фильтрация
        return STUDENT.stream().filter(transport -> transport.getType().equals("Грузовик")).collect(Collectors.toList());
    }

    public List<Transport> filterTransportNo18(){
        return STUDENT.stream().filter(transport -> transport.getType().equals("Автомобиль")).collect(Collectors.toList());
    }

    public List<Transport> filterTransportAlfavit(){
        Collections.sort(STUDENT, Comparator.comparing(Transport::getName));
        return STUDENT;
    }
}
