package com.example.nirgames.mapper;

import com.example.nirgames.dto.GameDto;
import com.example.nirgames.model.Game;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public static GameDto getDtoFromGame(Game game){
        return GameDto.builder()
                .genres(game.getGenres())
                .id(game.getId())
                .releasedAt(game.getReleasedAt())
                .title(game.getTitle())
                .build();
    }
    public static Game getGameFromDto(GameDto dto){
        return  Game.builder()
                .genres(dto.getGenres())
                .id(dto.getId())
                .releasedAt(dto.getReleasedAt())
                .title(dto.getTitle()).build();
    }

}
