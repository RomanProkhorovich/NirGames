package com.example.nirgames.controller;

import com.example.nirgames.dto.AuthDto;
import com.example.nirgames.dto.CustomerDto;
import com.example.nirgames.mapper.CustomerMapper;
import com.example.nirgames.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager manager;
    private final CustomerService service;
    private final CustomerMapper mapper;

    @PostMapping
    public CustomerDto authenticate(@RequestBody AuthDto authDto){
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
        Authentication auth = manager.authenticate(authentication);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        return mapper.map(service.findByUsername(authDto.getUsername()).get());
    }

}
