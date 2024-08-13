package com.example.nirgames.service;

import com.example.nirgames.model.Publisher;
import com.example.nirgames.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository repository;
    public List<Publisher> findAll(){
        return repository.findAll();
    }

    public Optional<Publisher> findByName(String name){
        return repository.findByName(name);
    }

    public Optional<Publisher> findById(Long id){
        return repository.findById(id);
    }

    public Publisher save(Publisher publisher){
        return repository.save(publisher);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
