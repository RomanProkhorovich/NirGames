package com.example.nirgames.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Year;

@Data
@Builder
public class DeveloperStudioDto {
    private Long id;
    private String studioName;
    private Year creationAt;
}
