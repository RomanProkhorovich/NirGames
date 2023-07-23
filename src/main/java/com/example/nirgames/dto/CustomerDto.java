package com.example.nirgames.dto;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
public class CustomerDto {
    private Long id;
    private String username;
    private Set<GameDto> favoriteGames = new LinkedHashSet<>();
}
