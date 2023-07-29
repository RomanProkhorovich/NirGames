package com.example.nirgames.dto;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String username;
    private String password;
    private Set<GameDto> favoriteGames = new LinkedHashSet<>();
}
