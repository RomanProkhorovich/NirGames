package com.example.nirgames.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
public class GameDto {
    private Long id;
    private String title;
    private Year releasedAt;
    @Builder.Default
    private Set<GenreDto> genres = new LinkedHashSet<>();
    private PublisherDto publisherDto;
    @Builder.Default
    private Set<DeveloperStudioDto> developerStudios = new LinkedHashSet<>();
}
