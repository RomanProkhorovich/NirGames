package com.example.nirgames.controller;

import com.example.nirgames.dto.CustomerDto;
import com.example.nirgames.dto.GameDto;
import com.example.nirgames.mapper.Mapper;
import com.example.nirgames.model.Customer;
import com.example.nirgames.model.Game;
import com.example.nirgames.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final CustomerService customerService;
    private final Mapper<Customer, CustomerDto> customerDtoMapper;
    private final Mapper<Game, GameDto> gameMapper;

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
}
