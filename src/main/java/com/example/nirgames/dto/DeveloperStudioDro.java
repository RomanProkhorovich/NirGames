package com.example.nirgames.dto;

import jakarta.persistence.Column;
import lombok.NonNull;

import java.time.Year;

public class DeveloperStudioDro {
    private Long id;

    @Column(unique = true, nullable = false)
    private String studioName;
    private Year creationAt;
}
