package com.example.nirgames.controller;

import com.example.nirgames.dto.GenreDto;
import com.example.nirgames.mapper.GenreMapper;
import com.example.nirgames.model.Genre;
import com.example.nirgames.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;
    private final GenreMapper mapper;
    @GetMapping
    public ResponseEntity<List<GenreDto>> getAll(){
        return ResponseEntity.ok(mapper.mapToDtosList(genreService.findAll()));
    }
}
