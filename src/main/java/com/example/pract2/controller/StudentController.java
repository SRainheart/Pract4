package com.example.pract2.controller;

import com.example.pract2.model.Book;
import com.example.pract2.model.StudentModel;
import com.example.pract2.model.Transport;
import com.example.pract2.service.BookServise;
import com.example.pract2.service.StudentServise;
import com.example.pract2.service.TransportServise;
import com.sun.source.tree.LambdaExpressionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    @Autowired
    private StudentServise studentServise;

    @GetMapping("/students")
    public String getAllStudents(Model module){
        module.addAttribute("students", studentServise.findAllStudent());//выгрузка студентов на экран
        return "StudentList";
    }

    @PostMapping("/students/add")
    public String addNewStudent(@RequestParam String name,
                                @RequestParam int age,
                                @RequestParam String lastname,
                                @RequestParam String middlename) {
        StudentModel newStudent = new StudentModel(0, name, age, lastname, middlename, false);// получаем данные с главных полей, id задается автоматически из нашего репозитория
        studentServise.addStudent(newStudent);// добавление студента в оперативную память(после перезапуска проекта, все данные стираются)
        return "redirect:/students";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными

    }

    @PostMapping("/students/update")
    public String updateNewStudent( @RequestParam int id,
                                @RequestParam String name,
                                @RequestParam int age,
                                @RequestParam String lastname,
                                @RequestParam String middlename) {
        StudentModel updatedStudent = new StudentModel(id, name, age, lastname, middlename, false);// Получаем новые данные из полей для обновления
        studentServise.updateStudent(updatedStudent);// Ссылаемся на сервис для обновления по id
        return "redirect:/students";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными

    }

    @PostMapping ("/students/delete")
    public String deleteStudent(@RequestParam int id){
        studentServise.deleteStudent(id);// Ссылаемся на сервис для удаления по id
        return "redirect:/students";//используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }

    @PostMapping("/students/id")
    public String findbyid(Model model, @RequestParam int id){
        model.addAttribute("students", studentServise.findStudentById(id));//выгрузка студентов на экран по id
        return "StudentList";
    }

    @PostMapping("/student/deleteAll")
    public String deleteStudentall(Model model){
        studentServise.deleteStudentAll();
        model.addAttribute("students", studentServise.findAllStudent());
        return "StudentList";
    }

    @PostMapping("/student/IsDeleteTrue")
    public String IsDeleteStudentTrue(@RequestParam int id,
                                      @RequestParam String name,
                                      @RequestParam int age,
                                      @RequestParam String lastname,
                                      @RequestParam String middlename){
        StudentModel IsdeleteTrue = new StudentModel(id, name, age, lastname, middlename, true);
        studentServise.IsDeleteTrue(IsdeleteTrue);
        return "redirect:/students";
    }

    @PostMapping("/student/filterStudentDelTwo")
    public String filterStudentDelTwo(Model model){
        model.addAttribute("students", studentServise.filterStudentsDelTwo());
        return "StudentList";
    }

    //Транспорт//////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    private TransportServise transportServise;

    @GetMapping("/tran")
    public String getAllTransport(Model model){
        model.addAttribute("transports", transportServise.findAllTransport());//выгрузка студентов на экран
        return "TransportList";
    }

    @PostMapping("/tran/add")
    public String addNewTransport(@RequestParam String name,
                                @RequestParam String type) {
        Transport newStudent = new Transport(0, name, type, false);// получаем данные с главных полей, id задается автоматически из нашего репозитория
        transportServise.addTransport(newStudent);// добавление студента в оперативную память(после перезапуска проекта, все данные стираются)
        return "redirect:/tran";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными

    }

    @PostMapping("/tran/update")
    public String updateNewTransport( @RequestParam int id,
                                    @RequestParam String name,
                                    @RequestParam String type) {
        Transport updatedStudent = new Transport(id, name, type, false);// Получаем новые данные из полей для обновления
        transportServise.updateTransport(updatedStudent);// Ссылаемся на сервис для обновления по id
        return "redirect:/tran";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными

    }

    @PostMapping ("/tran/delete")
    public String deleteTransport(@RequestParam int id){
        transportServise.deleteTransport(id);// Ссылаемся на сервис для удаления по id
        return "redirect:/tran";//используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }
    // книги/////////////////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    private BookServise bookServise;

    @GetMapping("/book")
    public String getAllBook(Model model){
        model.addAttribute("books", bookServise.findAllBook());//выгрузка студентов на экран
        return "BookList";
    }

    @PostMapping("/book/add")
    public String addNewBook(@RequestParam String name,
                                  @RequestParam String author,
                                  @RequestParam int price) {
        Book newStudent = new Book(0, name, author, price, false);// получаем данные с главных полей, id задается автоматически из нашего репозитория
        bookServise.addBook(newStudent);// добавление студента в оперативную память(после перезапуска проекта, все данные стираются)
        return "redirect:/book";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными

    }

    @PostMapping("/book/update")
    public String updateNewBook( @RequestParam int id,
                                 @RequestParam String name,
                                 @RequestParam String author,
                                 @RequestParam int price) {
        Book updatedStudent = new Book(id, name, author, price, false);// Получаем новые данные из полей для обновления
        bookServise.updateBook(updatedStudent);// Ссылаемся на сервис для обновления по id
        return "redirect:/book";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными

    }

    @PostMapping ("/book/delete")
    public String deleteBook(@RequestParam int id){
        bookServise.deleteBook(id);// Ссылаемся на сервис для удаления по id
        return "redirect:/book";//используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }
}
