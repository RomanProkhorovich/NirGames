package com.example.nirgames.dto;

import com.example.nirgames.model.Game;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class PublisherDto {

    private Long id;
    private String name;
    private Set<GameDto> games = new LinkedHashSet<>();
}
