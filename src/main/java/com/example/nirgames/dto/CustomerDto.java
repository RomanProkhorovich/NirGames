package com.example.nirgames.dto;

import com.example.nirgames.model.Role;
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
    private String firstname;
    private String lastname;

    private String username;
    private String password;
    private Role role;
    @Builder.Default
    private Set<GameDto> favoriteGames = new LinkedHashSet<>();
}
