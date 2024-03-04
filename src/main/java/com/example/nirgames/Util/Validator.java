package com.example.nirgames.Util;

import lombok.AllArgsConstructor;

import java.util.Arrays;
@AllArgsConstructor
public class Validator {
    private static final String[] unwantedWords = new String[0];
    public static   void validate(String text){
        if (Arrays.stream(unwantedWords).anyMatch(text::contains)){
            throw new RuntimeException("validate unsuccess");
        }
    }
}
