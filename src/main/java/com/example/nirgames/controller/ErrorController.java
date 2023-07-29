package com.example.nirgames.controller;

import com.example.nirgames.exceptions.ArgNotFoundException;
import com.example.nirgames.exceptions.CustomerAlreadyExistException;
import com.example.nirgames.exceptions.ReviewNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({ArgNotFoundException.class,
            AuthenticationException.class,
            NoSuchElementException.class,
            ReviewNotFoundException.class,
            UsernameNotFoundException.class})
    public String argException(Exception e,
                                 Model model) {
        model.addAttribute("ex", e.getMessage());
        return "exc";
    }
    @ExceptionHandler(CustomerAlreadyExistException.class)
    public String customerEx(Model model){
        model.addAttribute("ex","Пользователь с таким именем уже существует!");
        return "exc";
    }
}
