package com.example.nirgames.service;

import com.example.nirgames.Util.Validator;
import com.example.nirgames.model.Genre;
import com.example.nirgames.model.Review;
import com.example.nirgames.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;
    public Page<Review> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Review> findByTitle(String title){
        return repository.findByTitle(title);
    }

    public Optional<Review> findById(Long id){
        return repository.findById(id);
    }
    public Review save(Review review){
        Validator.validate(review.getText());
        return repository.save(review);
    }

    public Boolean isExist(String title){
        return repository.existsReviewByTitle(title);
    }
}
