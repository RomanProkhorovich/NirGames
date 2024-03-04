package com.example.nirgames.mapper;

import com.example.nirgames.dto.GameDto;
import com.example.nirgames.model.Game;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        DeveloperStudioMapper.class,
        CommentMapper.class,
        PublisherMapper.class,
        GenreMapper.class})
public interface GameMapper {
    Game map(GameDto dto);

    GameDto map(Game dto);

    List<Game> mapToEntityList(List<GameDto> dtos);

    List<GameDto> mapToDtosList(List<Game> games);
}
