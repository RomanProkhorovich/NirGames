package com.example.nirgames.service;

import com.example.nirgames.model.Genre;
import com.example.nirgames.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository repository;

    public List<Genre> findAll(){
        return repository.findAll();
    }

    public Optional<Genre> findByTitle(String title){
        return repository.findGenreByTitle(title);
    }

    public Optional<Genre> findById(Long id){
        return repository.findById(id);
    }
    public Genre save(Genre genre){
        return repository.save(genre);
    }
}
