package com.example.nirgames.dto;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
public class ReviewDto {
    private Long id;
    private String text;
    private GameDto game;
    private CustomerDto author;
    @Builder.Default
    private Set<CommentDto> comments = new LinkedHashSet<>();
    private String title;
    @Builder.Default
    private  Integer score = 0;
}
