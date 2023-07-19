package com.example.nirgames.repository;

import com.example.nirgames.model.DeveloperStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperStudioRepository extends JpaRepository<DeveloperStudio,Long> {
    Optional<DeveloperStudio> findByStudioName(String name);
}
