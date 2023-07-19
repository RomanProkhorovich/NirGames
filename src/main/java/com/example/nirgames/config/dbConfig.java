package com.example.nirgames.config;

import com.example.nirgames.model.Game;
import com.example.nirgames.model.Genre;
import com.example.nirgames.model.Publisher;
import com.example.nirgames.repository.GenreRepository;
import com.example.nirgames.repository.PublisherRepository;
import com.example.nirgames.service.GameService;
import com.example.nirgames.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Year;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class dbConfig {
    private final GenreService genreService;
    private final GameService gameService;
    private final PublisherRepository publisherRepository;
    @Bean
    public void initDb() throws Exception {
        Genre action = new Genre("Action");
        action=genreService.save(action);
        var a=genreService.findByTitle("Action")
                .orElseGet(()->genreService.save(new Genre("Action")));
        var publisher=publisherRepository.findByName("CD Project Red")
                .orElseGet(()->publisherRepository.save(new Publisher("Ubisoft")));
        Game doom=Game.builder()
                .genres(Set.of(a))
                .title("Doom 2015")
                .releasedAt(Year.of(2015))
                .publisher(publisher)
                .build();
        doom=gameService.save(doom);


//        Genre shooter=new Genre("Shooter");
//        shooter=genreService.save(shooter);
//        Game codMW = Game.builder()
//                .genres(Set.of(action,shooter))
//                .title("Call Of Duty: Modern Warfare")
//                .releasedAt(Year.of(2015))
//                /*.publisher(new Publisher("ActivisionBlizzard"))*/
//                .build();
//        codMW=gameService.save(codMW);
    }
}
