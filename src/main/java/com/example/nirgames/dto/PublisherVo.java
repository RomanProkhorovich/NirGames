package com.example.nirgames.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class PublisherVo {
    PublisherDto publisher;
    Page<GameDto> games;
}
