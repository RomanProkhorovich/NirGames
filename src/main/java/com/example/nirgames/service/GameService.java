package com.example.nirgames.service;

import com.example.nirgames.model.Customer;
import com.example.nirgames.model.DeveloperStudio;
import com.example.nirgames.model.Game;
import com.example.nirgames.model.Publisher;
import com.example.nirgames.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository repository;

    public Page<Game> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
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

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Set<Game> findByDeveloperStudio(DeveloperStudio studio){
        return repository.findAll()
                .stream()
                .filter(x->x.getDeveloperStudios()
                        .contains(studio))
                .collect(Collectors.toSet());
    }

    public  Page<Game> findByPublisher(Publisher publisher, Pageable pageable){
        return repository.findAllByPublisher(publisher, pageable);
    }
    public Page<Game> findByUser(Customer customer, Pageable pageable){
        return repository.findAllByUser(customer, pageable);
    }
    public Integer getScoreByGameId(Long id){
        return repository.getScoreByGameId(id);
    }
}
