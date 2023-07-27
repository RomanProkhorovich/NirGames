package com.example.nirgames.config;

import com.example.nirgames.model.*;
import com.example.nirgames.repository.RoleRepository;
import com.example.nirgames.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Year;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class dbConfig {
    private final GenreService genreService;
    private final GameService gameService;
    private final PublisherService publisherService;
    private final CommentService commentService;
    private final ReviewService reviewService;
    private final CustomerService customerService;
    private final DeveloperStudioService developerStudioService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public void initGenres() {
        genreService.save(new Genre("Action"));
        genreService.save(new Genre("RPG"));
        genreService.save(new Genre("Rogue-like"));
        genreService.save(new Genre("Souls-like"));
        genreService.save(new Genre("Shooter"));
        genreService.save(new Genre("Racing"));
    }

    @Bean
    public void initDeveloperStudio() {
        developerStudioService.save(DeveloperStudio.builder()
                .studioName("FromSoftware")
                .creationAt(Year.of(1986))
                .build());

        developerStudioService.save(DeveloperStudio.builder()
                .studioName("CD Project Red")
                .creationAt(Year.of(2002))
                .build());

        developerStudioService.save(DeveloperStudio.builder()
                .studioName("Rockstar Games")
                .creationAt(Year.of(1998))
                .build());

        developerStudioService.save(DeveloperStudio.builder()
                .studioName("Ubisoft")
                .creationAt(Year.of(1986))
                .build());


        developerStudioService.save(DeveloperStudio.builder()
                .studioName("Nintendo")
                .creationAt(Year.of(1889))
                .build());
    }

    @Bean
    public void initPublisher() {
        publisherService.save(Publisher.builder()
                .name("Ubisoft").build());

        publisherService.save(Publisher.builder()
                .name("Electronic Arts").build());

        publisherService.save(Publisher.builder()
                .name("Playstation Studio").build());


        publisherService.save(Publisher.builder()
                .name("Nintendo").build());

        publisherService.save(Publisher.builder()
                .name("CD Project Red").build());
    }

    @Bean
    public void initGames() {
        gameService.save(Game.builder()
                .title("Witcher 3")
                .releasedAt(Year.of(2015))
                .publisher(publisherService.findByName("CD Project Red").get())
                .developerStudios(Set.of(
                        developerStudioService
                                .findByStudioName("CD Project Red")
                                .get()))
                .genres(Set.of(
                        genreService.findByTitle("Action").get(),
                        genreService.findByTitle("RPG").get()))
                .build());
        gameService.save(Game.builder()
                .title("The Legend of Zelda: Breath of the Wild")
                .releasedAt(Year.of(2017))
                .developerStudios(Set.of(developerStudioService.findByStudioName("Nintendo").get()))
                .publisher(publisherService.save(publisherService.findByName("Nintendo").get()))
                .genres(Set.of(
                        genreService.findByTitle("RPG").get(),
                        genreService.findByTitle("Action").get()
                ))
                .build());
        gameService.save(Game.builder()
                .title("Bloodborne")
                .releasedAt(Year.of(2015))
                .publisher(publisherService.findByName("Playstation Studio").get())
                .developerStudios(Set.of(developerStudioService.findByStudioName("FromSoftware").get()))
                .genres(Set.of(
                        genreService.findByTitle("Action").get(),
                        genreService.findByTitle("Souls-like").get(),
                        genreService.findByTitle("RPG").get()
                )).build());

        gameService.save(Game.builder()
                .title("Dark Souls 3")
                .releasedAt(Year.of(2016))
                .publisher(publisherService.findByName("Playstation Studio").get())
                .developerStudios(Set.of(developerStudioService.findByStudioName("FromSoftware").get()))
                .genres(Set.of(
                        genreService.findByTitle("Action").get(),
                        genreService.findByTitle("Souls-like").get(),
                        genreService.findByTitle("RPG").get()
                )).build());
    }

    @Bean
    public void initRoles() {
        roleRepository.save(new Role("ROLE_ADMIN"));

    }

    @Bean
    public void initCustomer() {
        customerService.save(Customer.builder()
                .username("Roma")
                .favoriteGames(Set.of(
                        gameService.findByTitle("Bloodborne").get(),
                        gameService.findByTitle("Witcher 3").get()
                ))
                .password(passwordEncoder.encode("roma"))
                .roles(Set.of(roleRepository.findByRole("ROLE_ADMIN").get()))
                .build());

        customerService.save(Customer.builder()
                .username("Kostya")
                .roles(Set.of((roleRepository.findByRole("ROLE_ADMIN").get())))
                .favoriteGames(Set.of(
                        gameService.findByTitle("The Legend of Zelda: Breath of the Wild").get(),
                        gameService.findByTitle("Witcher 3").get()
                ))
                .password(passwordEncoder.encode("12345"))
                .build());
    }

    @Bean
    public void initReview() {
        reviewService.save(Review.builder()
                .author(customerService.findByUsername("Roma").get())
                .game(gameService.findByTitle("Witcher 3").get())
                .title("Любимая RPG")
                .text("uifertyuiogfdcvbnogiunehguehiuu v uuirehetuieh uerubthb hhhhh hhv4rgy34u n")
                .build());

        reviewService.save(Review.builder()
                .author(customerService.findByUsername("Roma").get())
                .game(gameService.findByTitle("Witcher 3").get())
                .title("Топ Механики")
                .text("uifertyuiogfdcvbnogiunehguehiuu v uuirehetuieh uerubthb hhhhh hhv4rgy34u n")
                .build());

        reviewService.save(Review.builder()
                .author(customerService.findByUsername("Kostya").get())
                .game(gameService.findByTitle("Bloodborne").get())
                .title("Hardcore")
                .text("uifertyre1222222222123344uiogfdcvbnogiunehguehiuu v uuirehetuieh uerubthb hhhhh hhv4rgy34u n")
                .build());
    }
}
