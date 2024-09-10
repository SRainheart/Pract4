package com.example.pract2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Size (max = 15, min = 3, message = "Имя должно быть больше или ровно 3 символа, но и не больше 15 символов")
    private String Name;
    @Min(value = 15, message = "Возраст студента должен быть больше 15 лет")
    @Max(value = 30, message = "Возраст студента должен быть меньше 30 лет")
    private int Age;
    @Size (min = 5, max = 25, message = "Фамилия должна быть больше или ровно 5 символа, но и не больше 25 символов")
    private String LastName;
    @Size (min = 5, max = 25, message = "Отчество должно быть больше или ровно 5 символа, но и не больше 25 символов")
    private String MiddleName;
    private boolean IsDelete;

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public String getLastName() {
        return LastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public boolean getDelete() {
        return IsDelete;
    }
    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public void setDelete(boolean dalete) {
        IsDelete = dalete;
    }
}
