package com.example.nirgames.controller;

import com.example.nirgames.dto.CustomerDto;
import com.example.nirgames.dto.GameDto;
import com.example.nirgames.mapper.CustomerMapper;
import com.example.nirgames.mapper.GameMapper;
import com.example.nirgames.model.Customer;
import com.example.nirgames.model.Game;
import com.example.nirgames.service.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final CustomerService customerService;
    private final CustomerMapper customerDtoMapper;
    private final GameMapper gameMapper;

    @GetMapping
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                              Model model){
        var username=userDetails.getUsername();
        var user=customerService.findByUsername(username).get();
        model.addAttribute("user",customerDtoMapper.map(user));
        return "user-info";
    }

    @GetMapping("/favorite_games")
    public String getUserGames(@AuthenticationPrincipal UserDetails userDetails,
                              Model model){
        var username=userDetails.getUsername();
        var user=customerService.findByUsername(username).get();
        var games=user.getFavoriteGames().stream().map(gameMapper::map);
        model.addAttribute("model",games);
        return "fav-games";
    }
    @PostMapping("/add-favorite")
    public ResponseEntity addFavoriteGame(@RequestBody CustomerGameDto dto){
        customerService.addFavoriteGame(dto.customerId, dto.gameId);
        return ResponseEntity.ok(null);
    }

    @Data
    private static class CustomerGameDto{
        Long customerId;
        Long gameId;
    }
}
