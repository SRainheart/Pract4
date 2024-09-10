package com.example.pract2.controller;

import com.example.pract2.model.*;
import com.example.pract2.repository.UserRepository;
import com.example.pract2.service.BookServise;
import com.example.pract2.service.StudentServise;
import com.example.pract2.service.TransportServise;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ControllerforAdmin {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentServise studentServise;
    @Autowired
    private TransportServise transportServise;
    @Autowired
    private BookServise bookServise;
    @GetMapping("/users")
    public String userView(Model model){
        model.addAttribute("user_list", userRepository.findAll());
        return "ListUsers";
    }
    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model) {
        Users users = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user_object", users);
        return "info";
    }

    @GetMapping("/{id}/update")
    public String updView(@PathVariable Long id, Model model) {
        Users user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user_object", user);
        model.addAttribute("roles", Roles.values());
        return "update";
    }
    @PostMapping("/{id}/update")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String username,
                             @RequestParam(name = "roles[]", required = false) String[] roles){
        System.out.println("*************************************************"+id);
        System.out.println("*************************************************"+username);
        Users users = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user ID: " + id));
        users.setUsername(username);
        users.getRoles().clear();
        if (roles != null){
            for (String role : roles){
                users.getRoles().add(Roles.valueOf(role));
            }
        }
        userRepository.save(users);
        return "redirect:/admin/" + id;
    }
    /////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/students")
    public String getAllStudents(Model module){
        List<StudentModel> students = studentServise.findAllStudent();
        module.addAttribute("students", students);//выгрузка студентов на экран
        module.addAttribute("student", new StudentModel());
        return "StudentList";
    }
    @PostMapping("/students/add")
    public String addNewStudent(@Valid @ModelAttribute StudentModel student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            List<StudentModel> students = studentServise.findAllStudent();
            model.addAttribute("students", students);
            model.addAttribute("student", student);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("message", objectError.getDefaultMessage());
            }
            return "redirect:/admin/students";
        }
        studentServise.addStudent(student);
        return "redirect:/admin/students";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }

    @PostMapping("/students/update/{id}")
    public String updateNewStudent(@Valid @ModelAttribute StudentModel student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            List<StudentModel> students = studentServise.findAllStudent();
            model.addAttribute("students", students);
            model.addAttribute("student", student);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_message", objectError.getDefaultMessage());
            }
            return "StudentList";
        }
        studentServise.updateStudent(student);// Ссылаемся на сервис для обновления по id
        return "redirect:/admin/students";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными

    }

    @PostMapping ("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentServise.deleteStudent(id);// Ссылаемся на сервис для удаления по id
        return "redirect:/admin/students";//используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
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

    @PostMapping("/student/deleteAll")
    public String deleteStudentall(Model model){
        studentServise.deleteStudentAll();
        model.addAttribute("students", studentServise.findAllStudent());
        return "redirect:/admin/students";
    }

    @PostMapping("/student/IsDeleteTrue/{id}")
    public String IsDeleteStudentTrue(@ModelAttribute StudentModel student){
        student.setDelete(true);
        studentServise.IsDeleteTrue(student);
        return "redirect:/admin/students";
    }
    @PostMapping("/student/filterStudentDelTwo")
    public String filterStudentDelTwo(Model model){
        List<StudentModel> students = studentServise.findAllStudent();
        model.addAttribute("students", students.stream()
                .filter(element -> element.getAge() >= 18)
                .collect(Collectors.toList()));//выгрузка студентов на экран по id
        model.addAttribute("student", new StudentModel());
        return "redirect:/admin/students";
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
    /////////////////////////////////////
    @GetMapping("/tran")
    public String getAllTransport(Model model){
        List<Transport> transports = transportServise.findAllTransport();
        model.addAttribute("transports", transports);//выгрузка студентов на экран
        model.addAttribute("transport", new Transport());
        return "TransportList";
    }

    @PostMapping("/tran/add")
    public String addNewTransport(@Valid @ModelAttribute Transport student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            List<Transport> transports = transportServise.findAllTransport();
            model.addAttribute("transports", transports);//выгрузка студентов на экран
            model.addAttribute("transport", new Transport());
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("message", objectError.getDefaultMessage());
            }
            return "TransportList";
        }
        transportServise.addTransport(student);
        return "redirect:/admin/tran";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }

    @PostMapping("/tran/update/{id}")
    public String updateNewTransport(@Valid @ModelAttribute Transport student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            List<Transport> students = transportServise.findAllTransport();
            model.addAttribute("transports", students);
            model.addAttribute("transport", student);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_message", objectError.getDefaultMessage());
            }
            return "TransportList";
        }
        transportServise.updateTransport(student);// Ссылаемся на сервис для обновления по id
        return "redirect:/admin/tran";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }

    @PostMapping ("/tran/delete/{id}")
    public String deleteTransport(@PathVariable Long id){
        transportServise.deleteTransport(id);// Ссылаемся на сервис для удаления по id
        return "redirect:/admin/tran";//используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
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

    @PostMapping("/tran/deleteAll")
    public String deleteTransportall(Model model){
        transportServise.deleteTransportAll();
        model.addAttribute("transports", transportServise.findAllTransport());
        return "redirect:/admin/tran";
    }

    @PostMapping("/tran/IsDeleteTrue/{id}")
    public String IsDeleteTransportTrue(@ModelAttribute Transport student){
        student.setDelete(true);
        transportServise.IsDeleteTransportTrue(student);
        return "redirect:/admin/tran";
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
    /////////////////////////////////////////////////////////////
    @GetMapping("/book")
    public String getAllBook(Model model){
        model.addAttribute("books", bookServise.findAllBook());//выгрузка студентов на экран
        return "BookList";
    }

    @PostMapping("/book/add")
    public String addNewBook(@Valid @ModelAttribute Book student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            List<Book> students = bookServise.findAllBook();
            model.addAttribute("books", students);
            model.addAttribute("book", student);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("message", objectError.getDefaultMessage());
            }
            return "redirect:/admin/book";
        }
        bookServise.addBook(student);
        return "redirect:/admin/book";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }

    @PostMapping("/book/update/{id}")
    public String updateNewBook(@Valid @ModelAttribute Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            List<Book> books = bookServise.findAllBook();
            model.addAttribute("books", books);
            model.addAttribute("book", book);
            for (final ObjectError objectError : bindingResult.getAllErrors()){
                model.addAttribute("up_message", objectError.getDefaultMessage());
            }
            return "BookList";
        }
        bookServise.updateBook(book);// Ссылаемся на сервис для обновления по id
        return "redirect:/admin/book";// используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
    }

    @PostMapping ("/book/delete/{id}")
    public String deleteBook(@RequestParam Long id){
        bookServise.deleteBook(id);// Ссылаемся на сервис для удаления по id
        return "redirect:/admin/book";//используем redirect на наш GetMapping, чтобы не создавать много однотипных страниц, считай просто презагружаем страницу с новыми данными
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

    @PostMapping("/book/deleteAll")
    public String deleteBookall(Model model){
        bookServise.deleteBookAll();
        model.addAttribute("books", bookServise.findAllBook());
        return "redirect:/admin/book";
    }

    @PostMapping("/book/IsDeleteTrue/{id}")
    public String IsDeleteBookTrue(@ModelAttribute Book student){
        student.setDelete(true);
        bookServise.IsDeleteBookTrue(student);
        return "redirect:/admin/book";
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
