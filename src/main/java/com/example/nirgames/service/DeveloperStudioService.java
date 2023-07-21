package com.example.nirgames.service;

import com.example.nirgames.model.DeveloperStudio;
import com.example.nirgames.repository.DeveloperStudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeveloperStudioService {
    private final DeveloperStudioRepository developerStudioRepository;

    public Optional<DeveloperStudio> findById(Long id){
        return developerStudioRepository.findById(id);
    }
    public Optional<DeveloperStudio> findByStudioName(String name){
        return developerStudioRepository.findByStudioName(name);
    }
    public List<DeveloperStudio> findAll(){
        return  developerStudioRepository.findAll();
    }
    public DeveloperStudio save(DeveloperStudio studio){
         return developerStudioRepository.save(studio);
    }
    public void delete(Long id){
        developerStudioRepository.deleteById(id);
    }
}
