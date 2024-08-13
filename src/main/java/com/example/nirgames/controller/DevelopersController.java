package com.example.nirgames.controller;

import com.example.nirgames.dto.DeveloperStudioDto;
import com.example.nirgames.mapper.DeveloperStudioMapper;
import com.example.nirgames.service.DeveloperStudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/devs")
public class DevelopersController {
    private final DeveloperStudioService service;
    private final DeveloperStudioMapper mapper;
    @GetMapping
    public ResponseEntity<List<DeveloperStudioDto>> getAll(){
        return ResponseEntity.ok(mapper.mapToDtosList(service.findAll()));
    }
}
