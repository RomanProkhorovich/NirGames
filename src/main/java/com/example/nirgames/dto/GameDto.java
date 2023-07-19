package com.example.nirgames.dto;

import com.example.nirgames.model.DeveloperStudio;
import com.example.nirgames.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDto {
    private Long id;
    private String title;
    private Year releasedAt;
    private Set<GenreDto> genres = new LinkedHashSet<>();
    private PublisherDto publisherDto;
    private Set<DeveloperStudioDro> developerStudios = new LinkedHashSet<>();
}
