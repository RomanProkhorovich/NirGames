package com.example.nirgames.mapper;

import com.example.nirgames.dto.DeveloperStudioDto;
import com.example.nirgames.dto.GameDto;
import com.example.nirgames.model.DeveloperStudio;
import com.example.nirgames.model.Game;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeveloperStudioMapper {

    DeveloperStudio map(DeveloperStudioDto dto);
    DeveloperStudioDto map(DeveloperStudio dto);
    List<DeveloperStudio> mapToEntityList(List<DeveloperStudioDto> dtos);
    List<DeveloperStudioDto> mapToDtosList(List<DeveloperStudio> games);
}
