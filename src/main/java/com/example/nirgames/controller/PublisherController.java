package com.example.nirgames.controller;

import com.example.nirgames.dto.GameDto;
import com.example.nirgames.dto.PublisherDto;
import com.example.nirgames.dto.PublisherVo;
import com.example.nirgames.mapper.GameMapper;
import com.example.nirgames.mapper.PublisherMapper;
import com.example.nirgames.model.Game;
import com.example.nirgames.service.GameService;
import com.example.nirgames.service.PublisherService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService service;
    private final PublisherMapper mapper;
    private final GameService gameService;
    private final GameMapper gameMapper;

    @GetMapping()
    public ResponseEntity<List<PublisherDto>> getAll() {
        return ResponseEntity.ok(mapper.mapToDtosList(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherVo> getById(@PathVariable("id") Long id,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);

        AtomicReference<PublisherVo> vo = new AtomicReference<>();
        service.findById(id).ifPresent(x -> {
            Page<Game> byPublisher = gameService.findByPublisher(x, paging);
            PublisherVo publisherVo = new PublisherVo(mapper.map(x), byPublisher.map(gameMapper::map));
            vo.set(publisherVo);
        });
        return ResponseEntity.ofNullable(vo.get());
    }

}
