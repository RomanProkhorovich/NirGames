package com.example.nirgames.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class securityConfig {
    private final UserDetailsService service;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        var dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(bCryptPasswordEncoder());
        dao.setUserDetailsService(service);
        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x
                        //.requestMatchers("/registration").permitAll()
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());
        //.logout(x -> x.permitAll()
        //        .logoutUrl("/sec/logout"))
        //.formLogin(x->x.permitAll());
        //.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
