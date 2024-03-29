package com.example.nirgames.controller;

import com.example.nirgames.dto.GameDto;
import com.example.nirgames.mapper.GameMapper;
import com.example.nirgames.model.Game;
import com.example.nirgames.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping({"/"})
public class MainController {
    private final GameService gameService;
    private final GameMapper gameMapper;

    @GetMapping
    public String findAll(Model model) {
        var allGame = gameService.findAll();
        var allDto = allGame.stream()
                .map(gameMapper::map)
                .collect(Collectors.toList());
        model.addAttribute("model", allDto);
        return "all-games";
    }

    @GetMapping("/games")
    public String findByTitle(@RequestParam("title") String title, Model model) {
        Game gameByTitle = gameService.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("что то пошло не так." +
                        " возможно такой игры мы еще не знаем." +
                        " вы можете добавить ее к нам в коллекцию)"));
        GameDto gameDto = gameMapper.map(gameByTitle);

        model.addAttribute("gameDto", gameDto);
        return "game";
    }
}
