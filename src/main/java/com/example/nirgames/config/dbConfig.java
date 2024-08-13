package com.example.nirgames.config;

import com.example.nirgames.model.*;
import com.example.nirgames.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Year;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class dbConfig implements CommandLineRunner {
    private final GenreService genreService;
    private final GameService gameService;
    private final PublisherService publisherService;
    private final CommentService commentService;
    private final ReviewService reviewService;
    private final CustomerService customerService;
    private final DeveloperStudioService developerStudioService;
    private final BCryptPasswordEncoder passwordEncoder;

    public void initGenres() {
        genreService.save(new Genre("Action"));
        genreService.save(new Genre("RPG"));
        genreService.save(new Genre("Rogue-like"));
        genreService.save(new Genre("Souls-like"));
        genreService.save(new Genre("Shooter"));
        genreService.save(new Genre("Racing"));
    }

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
                .studioName("Digital Extremes")
                .creationAt(Year.of(1986))
                .build());


        developerStudioService.save(DeveloperStudio.builder()
                .studioName("Nintendo")
                .creationAt(Year.of(1889))
                .build());
    }

    public void initPublisher() {
        publisherService.save(Publisher.builder().name("Digital Extremes").build());
        publisherService.save(Publisher.builder()
                .name("Ubisoft").build());

        publisherService.save(Publisher.builder()
                .name("Electronic Arts").build());
        publisherService.save(Publisher.builder()
                .name("Bandai Namco").build());

        publisherService.save(Publisher.builder()
                .name("Playstation Studio").build());


        publisherService.save(Publisher.builder()
                .name("Nintendo").build());

        publisherService.save(Publisher.builder()
                .name("CD Project Red").build());
    }

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
                .imgPath("4a2573f4e3fd.jpg")
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
                .imgPath("zelda.jpg")
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
                ))
                .imgPath("ITjiIA00EH767Frt-UO-tw.jpeg")
                .build());

        gameService.save(Game.builder()
                .title("Dark Souls 3")
                .releasedAt(Year.of(2016))
                .publisher(publisherService.findByName("Bandai Namco").get())
                .developerStudios(Set.of(developerStudioService.findByStudioName("FromSoftware").get()))
                .genres(Set.of(
                        genreService.findByTitle("Action").get(),
                        genreService.findByTitle("Souls-like").get(),
                        genreService.findByTitle("RPG").get()
                ))
                .imgPath("m1000x1000.jpg")
                .build());
        gameService.save(Game.builder()
                .title("Dark Souls 2")
                .releasedAt(Year.of(2014))
                .publisher(publisherService.findByName("Bandai Namco").get())
                .developerStudios(Set.of(developerStudioService.findByStudioName("FromSoftware").get()))
                .genres(Set.of(
                        genreService.findByTitle("Action").get(),
                        genreService.findByTitle("Souls-like").get(),
                        genreService.findByTitle("RPG").get()
                ))
                .imgPath("8700dc020d9dad7826c0d8ec9638001b.jpeg")
                .build());
        gameService.save(Game.builder()
                .title("Dark Souls")
                .releasedAt(Year.of(2011))
                .publisher(publisherService.findByName("Bandai Namco").get())
                .developerStudios(Set.of(developerStudioService.findByStudioName("FromSoftware").get()))
                .genres(Set.of(
                        genreService.findByTitle("Action").get(),
                        genreService.findByTitle("Souls-like").get(),
                        genreService.findByTitle("RPG").get()
                ))
                .imgPath("scale_1200.jpg")
                .build());
        gameService.save(Game.builder()
                .title("Warframe")
                .releasedAt(Year.of(2013))
                .publisher(publisherService.findByName("Digital Extremes").get())
                .developerStudios(Set.of(developerStudioService.findByStudioName("Digital Extremes").get()))
                .genres(Set.of(
                        genreService.findByTitle("Action").get(),
                        genreService.findByTitle("RPG").get()
                ))
                .imgPath("image.jpg")
                .build());
    }

    public void initCustomer() {
        customerService.save(Customer.builder()
                .username("Roma")
                .favoriteGames(Set.of(
                        gameService.findByTitle("Bloodborne").get(),
                        gameService.findByTitle("Witcher 3").get()
                ))
                .firstname("Роман")
                .lastname("Прохорович")
                .password(passwordEncoder.encode("roma"))
                .role(Role.ADMIN)
                .build());

        customerService.save(Customer.builder()
                .username("Kostya")
                .role(Role.USER)
                .favoriteGames(Set.of(
                        gameService.findByTitle("The Legend of Zelda: Breath of the Wild").get(),
                        gameService.findByTitle("Witcher 3").get()
                ))
                .password(passwordEncoder.encode("12345"))
                .build());
    }

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


    }

    @Override
    public void run(String... args) throws Exception {
        initPublisher();
        initGenres();
        initDeveloperStudio();
        initGames();
        initCustomer();
        initReview();
    }
}
