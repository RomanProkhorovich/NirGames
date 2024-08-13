package com.example.nirgames.controller;

import com.example.nirgames.dto.GameDto;
import com.example.nirgames.dto.ReviewDto;
import com.example.nirgames.exceptions.ArgNotFoundException;
import com.example.nirgames.mapper.ReviewMapper;
import com.example.nirgames.model.Review;
import com.example.nirgames.service.CustomerService;
import com.example.nirgames.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final CustomerService customerService;
    private final ReviewMapper reviewMapper;

    @GetMapping
    public Page<ReviewDto> getAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        var allGame = reviewService.findAll(paging);

        Page<ReviewDto> dtoPage = allGame.map(reviewMapper::map);
        return dtoPage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> findByTitle(@PathVariable Long id) {
        var review = reviewService.findById(id).orElseThrow(() ->
                new NoSuchElementException("Статьи с таким именем нет!"));
        return ResponseEntity.ok(reviewMapper.map(review));
    }

    @PostMapping("/add")
    public ResponseEntity<ReviewDto> saveReview(@RequestBody ReviewDto reviewDto,
                             @AuthenticationPrincipal UserDetails userDetails) throws AuthenticationException {
        String text = reviewDto.getText();
        GameDto game = reviewDto.getGame();
        String title = reviewDto.getTitle();
        if (text == null || text.isEmpty() || game == null || title == null || title.isEmpty())
            throw new ArgNotFoundException("Вы должны указать текст и игру");

        var review = reviewMapper.map(reviewDto);
        if (reviewDto.getAuthor() == null) {
            var username = userDetails.getUsername();
            var customer = customerService.findByUsername(username)
                    .orElseThrow(AuthenticationException::new);
            review.setAuthor(customer);
        }
        review = reviewService.save(review);


        return ResponseEntity.ok(reviewMapper.map(review));
    }


}
