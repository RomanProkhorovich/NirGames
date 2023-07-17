package com.example.nirgames.config;

import com.example.nirgames.model.Game;
import com.example.nirgames.model.Genre;
import com.example.nirgames.repository.GameRepository;
import com.example.nirgames.repository.GenreRepository;
import com.example.nirgames.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Year;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class dbConfig {
    private final GenreRepository genreRepository;
    private final GameService gameService;
    @Bean
    public void initDb(){
        Genre action = new Genre();
        action.setTitle("Action");
        action=genreRepository.save(action);

        Game doom=Game.builder()
                .genres(Set.of(action))
                .title("Doom 2015")
                .releasedAt(Year.of(2015))
                .build();
        doom=gameService.save(doom);

        Genre act=new Genre();
        act.setTitle("Action");

        Genre shooter=new Genre();
        shooter.setTitle("Shooter");
        Game codMW = Game.builder()
                .genres(Set.of(act,shooter))
                .title("Call Of Duty: Modern Warfare")
                .releasedAt(Year.of(2015))
                .build();
        codMW=gameService.save(codMW);
    }
}
