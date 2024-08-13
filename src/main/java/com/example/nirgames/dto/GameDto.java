package com.example.nirgames.dto;

import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
public class GameDto {
    private Long id;
    private String title;
    private Year releasedAt;
    private Set<GenreDto> genres ;
    private PublisherDto publisher;
    private Set<DeveloperStudioDto> developerStudios;
    private File img;
    private String imgPath;
}
