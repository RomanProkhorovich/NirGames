package com.example.nirgames.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice

public class ErrorController {
    @ExceptionHandler(NoSuchElementException.class)
    public String elementNotFound(NoSuchElementException e, Model model){

        model.addAttribute("ex",e.getMessage());
        return "exc";
    }
}
