package com.example.nirgames.service;

import com.example.nirgames.model.Game;
import com.example.nirgames.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository repository;

    public List<Game> findAll() {
        return repository.findAll();
    }

    public Optional<Game> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Game> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public Game save(Game game) {
        return repository.save(game);
    }

}
