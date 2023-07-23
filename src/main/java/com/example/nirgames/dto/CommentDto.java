package com.example.nirgames.dto;

import com.example.nirgames.model.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String text;
    private CustomerDto customer;
}
