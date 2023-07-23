package com.example.nirgames.controller;

import com.example.nirgames.dto.CustomerDto;
import com.example.nirgames.dto.GameDto;
import com.example.nirgames.dto.ReviewDto;
import com.example.nirgames.exceptions.ArgNotFoundException;
import com.example.nirgames.mapper.Mapper;
import com.example.nirgames.model.Customer;
import com.example.nirgames.model.Review;
import com.example.nirgames.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final Mapper<ReviewDto, Review> dtoToReview;
    private final Mapper<Review, ReviewDto> reviewToDto;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("model",reviewService.findAll()
                .stream()
                .map(reviewToDto::map));
        return "all_reviews";
    }

    @GetMapping("/{title}")
    public String findByTitle(@PathVariable String title, Model model){
        var review =reviewService.findByTitle(title).orElseThrow(()->
                new NoSuchElementException("Статьи с таким именем нет!"));
        model.addAttribute("rev",reviewToDto.map(review));
        return "review";
    }

    @PostMapping("/add")
    public String saveReview(@ModelAttribute ReviewDto reviewDto, Model model){
        //CustomerDto author = reviewDto.getAuthor();
        //TODO: получить автора из контекста аутификации
        String text = reviewDto.getText();
        GameDto game = reviewDto.getGame();
        String title = reviewDto.getTitle();
        if ( text==null || text.isEmpty() || game==null || title==null || title.isEmpty())
            throw new ArgNotFoundException("Вы должны указать текст и игру");

        var review = reviewService.save(dtoToReview.map(reviewDto));
        review.setAuthor(Customer.builder().username(UUID.randomUUID().toString()).build());
        model.addAttribute("rev",review);
        return "review";
    }


}
