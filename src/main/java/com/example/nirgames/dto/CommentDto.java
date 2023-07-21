package com.example.nirgames.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String text;
    private CustomerDto customer;
    private GameDto game;
}
