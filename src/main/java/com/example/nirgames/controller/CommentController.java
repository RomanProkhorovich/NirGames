package com.example.nirgames.controller;

import com.example.nirgames.dto.CommentDto;
import com.example.nirgames.exceptions.ReviewNotFoundException;
import com.example.nirgames.mapper.CommentMapper;
import com.example.nirgames.mapper.CustomerMapper;
import com.example.nirgames.mapper.GameMapper;
import com.example.nirgames.model.Comment;
import com.example.nirgames.model.Review;
import com.example.nirgames.service.CommentService;
import com.example.nirgames.service.CustomerService;
import com.example.nirgames.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews/{reviewId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final ReviewService reviewService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    private final CommentMapper commentToDto;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllCommentsByReview(@PathVariable String title) {
        var review = new Review();
        reviewService.findByTitle(title).orElseThrow(() ->
                new ReviewNotFoundException("Такой статьи мы не знаем"));
        var comments = review.getComments();
        var commentsDto = comments.stream().map(commentToDto::map).collect(Collectors.toList());
        return ResponseEntity.ok(commentsDto);
    }

    @PostMapping
    public ResponseEntity addComment(@PathVariable("reviewId") Long reviewId,@RequestBody CommentDto dto) throws AuthenticationException {

        var review = reviewService.findById(reviewId).orElseThrow(() ->
                new NoSuchElementException("Такой статьи мы не знаем!"));


        Comment comment = Comment.builder()
                .text(dto.getText())
                .customer(customerMapper.map(dto.getCustomer()))
                .build();
        comment = commentService.save(comment);
        review.addComment(comment);
        review = reviewService.save(review);
        return ResponseEntity.ok("");
    }
}
