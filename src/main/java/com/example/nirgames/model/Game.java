package com.example.nirgames.model;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.Hibernate;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Класс-сущность, который представляет собой игру
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * поля с названием игры. должно быть уникальным. также по этому полю определен equals
     */
    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private Year releasedAt;

    @Builder.Default
    @ManyToMany(cascade = { CascadeType.MERGE})
    @JoinTable(name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "genres_id",nullable = false))
    private Set<Genre> genres = new LinkedHashSet<>();

    @ManyToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<DeveloperStudio> developerStudios = new LinkedHashSet<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "rec_system_requries_id")
    private SystemRequries recSystemRequries;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "min_system_requries_id")
    private SystemRequries minSystemRequries;

    private String imgPath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Game game = (Game) o;
        return id != null && Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
