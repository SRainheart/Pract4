package com.example.pract2.controller;

import com.example.pract2.model.Roles;
import com.example.pract2.model.Users;
import com.example.pract2.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController implements WebMvcConfigurer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String regView() {
        return "regis";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout"; // Перенаправление на страницу логина после выхода
    }

    @PostMapping("/registration")
    public String reg(@Valid Users user, Model model) {
        Pattern p = Pattern.compile("[!@#$%&*()_+=|]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(user.getPassword());
        boolean b = m.find();
        if (user.getPassword().length()<8){
            model.addAttribute("message", "Минимальная длинна пароля 8 символов");
            return "regis";
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }
        if(b){
            user.setActive(true);
            user.setRoles(Collections.singleton(Roles.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/login";
        }
        model.addAttribute("message", "Добавьте специальные символы");
        return "regis";
    }
}
