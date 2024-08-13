package com.example.nirgames.repository;

import com.example.nirgames.model.Customer;
import com.example.nirgames.model.Game;
import com.example.nirgames.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    Optional<Game> findByTitle(String title);
    Page<Game> findAll(Pageable pageable);

    Page<Game> findAllByPublisher(Publisher publisher, Pageable pageable);
    @Query("select p.favoriteGames from Customer p where p = ?1")
    Page<Game> findAllByUser(Customer customer, Pageable pageable);
    @Query("select(" +
            "(select sum(r.score) from Review r group by r.game.id HAVING r.game.id = ?1)" +
            "/(select count(*) from Review r group by r.game.id HAVING r.game.id = ?1)" +
            ")")
    Integer getScoreByGameId(Long gameId);
}
