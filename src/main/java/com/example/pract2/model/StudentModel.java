package com.example.pract2.model;

public class StudentModel {
    private int Id;
    private String Name;
    private int Age;
    private String LastName;
    private String MiddleName;
    private boolean IsDelete;

    public StudentModel(int id, String name, int age, String lastName, String middleName, boolean isDelete) {
        Id = id;
        Name = name;
        Age = age;
        LastName = lastName;
        MiddleName = middleName;
        IsDelete = isDelete;
    }

    public int getId() {
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
    public void setId(int id) {
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
