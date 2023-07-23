package com.example.nirgames.controller;

import com.example.nirgames.dto.CommentDto;
import com.example.nirgames.exceptions.ReviewNotFoundException;
import com.example.nirgames.mapper.Mapper;
import com.example.nirgames.model.Comment;
import com.example.nirgames.model.Customer;
import com.example.nirgames.model.Review;
import com.example.nirgames.service.CommentService;
import com.example.nirgames.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews/{title}/comments")
public class CommentController {

    private final CommentService commentService;
    private final ReviewService reviewService;

    private final Mapper<Comment, CommentDto> commentToDto;
    @GetMapping
    public String getAllCommentsByReview(Model model, @PathVariable String title){
        var review= new Review();
                reviewService.findByTitle(title).orElseThrow(()->
                new ReviewNotFoundException("Такой статьи мы не знаем"));
        var comments = review.getComments();
        var commentsDto= comments.stream().map(commentToDto::map);
        model.addAttribute("comments",commentsDto);
        return "comments";
    }

    @PostMapping
    public String addComment(@ModelAttribute String text, @PathVariable String title){
        var review=reviewService.findByTitle(title).orElseThrow(()->
                new NoSuchElementException("Такой статьи мы не знаем!"));
        Comment comment=Comment.builder()
                .text(text)
                .customer(Customer.builder()
                        .username(UUID.randomUUID().toString())
                        .build()) //TODO:получить пользователя из контекста
                .build();
        comment=commentService.save(comment);
        review.addComment(comment);
        reviewService.save(review);
        //TODO: add html
        return "review";
    }
}
