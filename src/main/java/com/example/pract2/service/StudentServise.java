package com.example.pract2.service;

import com.example.pract2.model.StudentModel;

import java.util.List;

public interface StudentServise {
    public List<StudentModel> findAllStudent();
    public StudentModel findStudentById(int id);
    public StudentModel addStudent(StudentModel student);
    public StudentModel updateStudent(StudentModel student);

    void deleteStudent(int id);

    void deleteStudentAll();
    public StudentModel IsDeleteTrue(StudentModel student);
    public List<StudentModel> filterStudentsDelTwo();
}
