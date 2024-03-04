package com.example.nirgames.controller;

import com.example.nirgames.dto.CommentDto;
import com.example.nirgames.exceptions.ReviewNotFoundException;
import com.example.nirgames.mapper.CommentMapper;
import com.example.nirgames.mapper.GameMapper;
import com.example.nirgames.model.Comment;
import com.example.nirgames.model.Review;
import com.example.nirgames.service.CommentService;
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
@RequestMapping("/reviews/{title}/comments")
public class CommentController {

    private final CommentService commentService;
    private final ReviewService reviewService;
    private final CustomerService customerService;

    private final CommentMapper commentToDto;

    @GetMapping
    public String getAllCommentsByReview(Model model, @PathVariable String title) {
        var review = new Review();
        reviewService.findByTitle(title).orElseThrow(() ->
                new ReviewNotFoundException("Такой статьи мы не знаем"));
        var comments = review.getComments();
        var commentsDto = comments.stream().map(commentToDto::map);
        model.addAttribute("comments", commentsDto);
        return "comments";
    }

    @PostMapping
    public String addComment(@ModelAttribute String text,
                             @PathVariable String title,
                             @AuthenticationPrincipal UserDetails userDetails,
                             Model model) throws AuthenticationException {

        var review = reviewService.findByTitle(title).orElseThrow(() ->
                new NoSuchElementException("Такой статьи мы не знаем!"));

        var username = userDetails.getUsername();
        var customer = customerService.findByUsername(username)
                .orElseThrow(AuthenticationException::new);
        Comment comment = Comment.builder()
                .text(text)
                .customer(customer)
                .build();
        comment = commentService.save(comment);
        review.addComment(comment);
        review = reviewService.save(review);
        model.addAttribute("rev", review);
        return "review";
    }
}
