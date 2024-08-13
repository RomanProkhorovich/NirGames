package com.example.nirgames.repository;

import com.example.nirgames.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAll(Pageable pageable);
    Optional<Review> findByTitle(String title);
    Boolean existsReviewByTitle(String title);
}
