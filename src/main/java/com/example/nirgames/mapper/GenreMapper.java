package com.example.nirgames.mapper;

import com.example.nirgames.dto.GenreDto;
import com.example.nirgames.model.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    Genre map(GenreDto dto);
    GenreDto map(Genre dto);
    List<Genre> mapToEntityList(List<GenreDto> dtos);
    List<GenreDto> mapToDtosList(List<Genre> games);
}
