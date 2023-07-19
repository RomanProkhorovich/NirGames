package com.example.nirgames.config;

import com.example.nirgames.dto.GameDto;
import com.example.nirgames.dto.GenreDto;
import com.example.nirgames.mapper.Mapper;
import com.example.nirgames.model.Game;
import com.example.nirgames.model.Genre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class MappersConfig {
    @Bean
    public Mapper<GameDto, Game> gameDtoToGameMapper(Mapper<GenreDto,Genre> dtoToGenre){
        return obj -> Game.builder()
                .genres(obj.getGenres()
                        .stream()
                        .map(dtoToGenre::map)
                        .collect(Collectors.toSet()))
                .id(obj.getId())
                .releasedAt(obj.getReleasedAt())
                .title(obj.getTitle())
                .build();
    }
    @Bean
    public Mapper<Game,GameDto> gameToGameDtoMapper(Mapper<Genre, GenreDto> genreToDto){
        return obj -> GameDto.builder()
                .genres(obj.getGenres()
                        .stream()
                        .map(genreToDto::map)
                        .collect(Collectors.toSet()))
                .id(obj.getId())
                .releasedAt(obj.getReleasedAt())
                .title(obj.getTitle())
                .build();
    }

    @Bean
    public Mapper<Genre,GenreDto> genreToGenreDtoMapper() {
        return dto->
                new GenreDto(dto.getId(),dto.getTitle());
    }

    @Bean
    public Mapper<GenreDto,Genre> genreDtoToGenreMapper() {
        return genre->
                new Genre(genre.getId(),genre.getTitle());
    }
}
