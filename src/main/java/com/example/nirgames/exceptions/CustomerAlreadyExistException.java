package com.example.nirgames.exceptions;


public class CustomerAlreadyExistException extends RuntimeException {
    public CustomerAlreadyExistException() {
    }

    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
