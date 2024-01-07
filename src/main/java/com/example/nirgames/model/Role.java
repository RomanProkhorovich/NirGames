package com.example.nirgames.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

public enum Role {
    ADMIN("Админ"),
    USER("Пользователь");


    String role;

    Role(String role) {

    }
}
