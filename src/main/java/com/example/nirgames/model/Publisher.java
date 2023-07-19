package com.example.nirgames.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

/*    @OneToMany(mappedBy = "publisher")
    private Set<Game> games = new LinkedHashSet<>();*/

    public Publisher(String name, Set<Game> games) {
        this.name = name;/*
        this.games = games;*/
    }

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return name.equals(publisher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
