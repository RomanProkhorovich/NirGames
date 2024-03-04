package com.example.nirgames.controller;

import com.example.nirgames.dto.GameDto;
import com.example.nirgames.dto.ReviewDto;
import com.example.nirgames.exceptions.ArgNotFoundException;
import com.example.nirgames.mapper.GameMapper;
import com.example.nirgames.mapper.ReviewMapper;
import com.example.nirgames.model.Review;
import com.example.nirgames.service.CustomerService;
import com.example.nirgames.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
    @RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final CustomerService customerService;
    private final ReviewMapper reviewMapper;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("model", reviewService.findAll()
                .stream()
                .map(reviewMapper::map));
        return "all_reviews";
    }

    @GetMapping("/{title}")
    public String findByTitle(@PathVariable String title, Model model) {
        var review = reviewService.findByTitle(title).orElseThrow(() ->
                new NoSuchElementException("Статьи с таким именем нет!"));
        model.addAttribute("rev", reviewMapper.map(review));
        return "review";
    }

    @PostMapping("/add")
    public String saveReview(@ModelAttribute ReviewDto reviewDto,
                             Model model,
                             @AuthenticationPrincipal UserDetails userDetails) throws AuthenticationException {
        String text = reviewDto.getText();
        GameDto game = reviewDto.getGame();
        String title = reviewDto.getTitle();
        if (text == null || text.isEmpty() || game == null || title == null || title.isEmpty())
            throw new ArgNotFoundException("Вы должны указать текст и игру");

        var username = userDetails.getUsername();
        var customer = customerService.findByUsername(username)
                .orElseThrow(AuthenticationException::new);
        var review = reviewMapper.map(reviewDto);
        review.setAuthor(customer);
        review = reviewService.save(review);
        model.addAttribute("rev", review);
        return "review";
    }


}
