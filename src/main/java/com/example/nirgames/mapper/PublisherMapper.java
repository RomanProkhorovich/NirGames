package com.example.nirgames.mapper;

import com.example.nirgames.dto.GenreDto;
import com.example.nirgames.dto.PublisherDto;
import com.example.nirgames.model.Genre;
import com.example.nirgames.model.Publisher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    Publisher map(PublisherDto dto);
    PublisherDto map(Publisher dto);
    List<Publisher> mapToEntityList(List<PublisherDto> dtos);
    List<PublisherDto> mapToDtosList(List<Publisher> games);
}
