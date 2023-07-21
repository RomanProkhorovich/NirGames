package com.example.nirgames.repository;

import com.example.nirgames.model.Game;
import com.example.nirgames.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    Optional<Game> findByTitle(String title);

    List<Game> findAllByPublisher(Publisher publisher);


}
