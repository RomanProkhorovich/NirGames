package com.example.nirgames.repository;

import com.example.nirgames.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
    Genre findGenreByTitle(String title);
}
