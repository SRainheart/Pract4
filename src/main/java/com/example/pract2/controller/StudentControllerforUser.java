package com.example.pract2.controller;
import com.example.pract2.model.Book;
import com.example.pract2.model.StudentModel;
import com.example.pract2.model.Transport;
import com.example.pract2.service.BookServise;
import com.example.pract2.service.StudentServise;
import com.example.pract2.service.TransportServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('USER')")
public class StudentControllerforUser {
    @Autowired
    private StudentServise studentServise;
    @GetMapping("/students")
    public String getAllStudents(Model module){
        List<StudentModel> students = studentServise.findAllStudent();
        module.addAttribute("students", students);//выгрузка студентов на экран
        module.addAttribute("student", new StudentModel());
        return "StudentList";
    }
    @PostMapping("/students/id")
    public String findbyid(Model model, @RequestParam String id){
        List<StudentModel> students = studentServise.findAllStudent();
        model.addAttribute("students", students.stream()
                .filter(element -> element.getLastName().equals(id))
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("student", new StudentModel());
        return "StudentList";
    }
    @PostMapping("/student/filterStudentDelTwo")
    public String filterStudentDelTwo(Model model){
        List<StudentModel> students = studentServise.findAllStudent();
        model.addAttribute("students", students.stream()
                .filter(element -> element.getAge() >= 18)
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("student", new StudentModel());
        return "StudentList";
    }
    @PostMapping("/student/filterStudentNot")
    public String filterStudentNot18(Model model){
        List<StudentModel> students = studentServise.findAllStudent();
        model.addAttribute("students", students.stream()
                .filter(element -> element.getAge() < 18)
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("student", new StudentModel());
        return "StudentList";
    }
    @PostMapping("/student/filterStudentSort")
    public String filterStudentSort(Model model){
        List<StudentModel> students = studentServise.findAllStudent();
        Collections.sort(students, Comparator.comparing(StudentModel :: getLastName));
        model.addAttribute("students", students);//выгрузка студентов на экран по id
        model.addAttribute("student", new StudentModel());
        return "StudentList";
    }

    //Транспорт//////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    private TransportServise transportServise;

    @GetMapping("/tran")
    public String getAllTransport(Model model){
        List<Transport> transports = transportServise.findAllTransport();
        model.addAttribute("transports", transports);//выгрузка студентов на экран
        model.addAttribute("transport", new Transport());
        return "TransportList";
    }
    @PostMapping("/tran/id")
    public String findTransport(Model model, @RequestParam String id){
        List<Transport> students = transportServise.findAllTransport();
        model.addAttribute("transports", students.stream()
                .filter(element -> element.getName().equals(id))
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("transport", new Transport());
        return "TransportList";
    }
    @PostMapping("/tran/filterStudentDelTwo")
    public String filterTransportDelTwo(Model model){
        List<Transport> students = transportServise.findAllTransport();
        model.addAttribute("transports", students.stream()
                .filter(element -> element.getType().equals("Грузовик"))
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("transport", new Transport());
        return "TransportList";
    }

    @PostMapping("/tran/filterStudentNot")
    public String filterTransportNot18(Model model){
        List<Transport> students = transportServise.findAllTransport();
        model.addAttribute("transports", students.stream()
                .filter(element -> element.getType().equals("Автомобиль"))
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("transport", new Transport());
        return "TransportList";
    }

    @PostMapping("/tran/filterStudentSort")
    public String filterTransportSort(Model model){
        List<Transport> students = transportServise.findAllTransport();
        Collections.sort(students, Comparator.comparing(Transport :: getName));
        model.addAttribute("transports", students);//выгрузка студентов на экран по id
        model.addAttribute("transport", new Transport());
        return "TransportList";
    }
    // книги/////////////////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    private BookServise bookServise;

    @GetMapping("/book")
    public String getAllBook(Model model){
        model.addAttribute("books", bookServise.findAllBook());//выгрузка студентов на экран
        return "BookList";
    }
    @PostMapping("/book/id")
    public String findBook(Model model, @RequestParam String id){
        List<Book> books = bookServise.findAllBook();
        model.addAttribute("books", books.stream()
                .filter(element -> element.getName().equals(id))
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("book", new Book());
        return "BookList";
    }
    @PostMapping("/book/filterStudentDelTwo")
    public String filterBookDelTwo(Model model){
        List<Book> students = bookServise.findAllBook();
        model.addAttribute("books", students.stream()
                .filter(element -> element.getPrice() >= 1000)
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("book", new Book());
        return "BookList";
    }

    @PostMapping("/book/filterStudentNot")
    public String filterBookNot18(Model model){
        List<Book> students = bookServise.findAllBook();
        model.addAttribute("books", students.stream()
                .filter(element -> element.getPrice() < 1000)
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("book", new Book());
        return "BookList";
    }

    @PostMapping("/book/filterStudentSort")
    public String filterBookSort(Model model){
        List<Book> students = bookServise.findAllBook();
        Collections.sort(students, Comparator.comparing(Book :: getName));
        model.addAttribute("books", students);//выгрузка студентов на экран по id
        model.addAttribute("book", new Book());
        return "BookList";
    }
}
