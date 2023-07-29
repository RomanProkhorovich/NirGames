package com.example.nirgames.config;

import com.example.nirgames.dto.*;
import com.example.nirgames.mapper.Mapper;
import com.example.nirgames.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class MappersConfig {
    @Bean
    public Mapper<GameDto, Game> gameDtoToGameMapper(Mapper<GenreDto,Genre> dtoToGenre,
                                                     Mapper<PublisherDto,Publisher> publisherMapper,
                                                     Mapper<DeveloperStudioDto,DeveloperStudio> developerStudioMapper){
        return obj -> Game.builder()
                .genres(obj.getGenres()
                        .stream()
                        .map(dtoToGenre::map)
                        .collect(Collectors.toSet()))
                .id(obj.getId())
                .publisher(publisherMapper.map(obj.getPublisherDto()))
                .releasedAt(obj.getReleasedAt())
                .developerStudios(obj.getDeveloperStudios().stream()
                        .map(developerStudioMapper::map)
                        .collect(Collectors.toSet()))
                .title(obj.getTitle())
                .build();
    }
    @Bean
    public Mapper<Game,GameDto> gameToGameDtoMapper(Mapper<Genre, GenreDto> genreToDto,
                                                    Mapper<Publisher,PublisherDto> publisherMapper,
                                                    Mapper<DeveloperStudio,DeveloperStudioDto> developerStudioDtoMapper){
        return obj -> GameDto.builder()
                .genres(obj.getGenres()
                        .stream()
                        .map(genreToDto::map)
                        .collect(Collectors.toSet()))
                .id(obj.getId())
                .releasedAt(obj.getReleasedAt())
                .title(obj.getTitle())
                .developerStudios(obj.getDeveloperStudios().stream()
                        .map(developerStudioDtoMapper::map)
                        .collect(Collectors.toSet()))
                .publisherDto(publisherMapper.map(obj.getPublisher()))
                .build();
    }

    @Bean
    public Mapper<Genre,GenreDto> genreToGenreDtoMapper() {
        return dto->
                new GenreDto(dto.getId(),dto.getTitle());
    }

    @Bean
    public Mapper<GenreDto,Genre> genreDtoToGenreMapper() {
        return genre->
                new Genre(genre.getId(),genre.getTitle());
    }


    @Bean
    public Mapper<Comment, CommentDto> commentToCommentDtoMapper(Mapper<Customer, CustomerDto> customerToDtoMapper) {
        return comment->
                CommentDto.builder()
                        .customer(customerToDtoMapper.map(comment.getCustomer()))
                        .text(comment.getText())
                        .id(comment.getId())
                        .build();
    }

    @Bean
    public Mapper<CommentDto,Comment> commentDtoToCommentMapper(Mapper<CustomerDto, Customer> customerMapper){
        return dto->
                Comment.builder()
                        .text(dto.getText())
                        .customer(customerMapper.map(dto.getCustomer()))
                        .id(dto.getId())
                        .build();
    }

    @Bean
    public Mapper<CustomerDto,Customer> customerDtoMapper(Mapper<GameDto,Game> gameMapper){
        return obj->
            Customer.builder()
                    .id(obj.getId())
                    .username(obj.getUsername())
                    .password(obj.getPassword())
                    .favoriteGames(obj.getFavoriteGames()
                            .stream()
                            .map(gameMapper::map)
                            .collect(Collectors.toSet()))
                    .build();

    }

    @Bean
    public Mapper<Customer,CustomerDto> customerToDtoMapper(Mapper<Game,GameDto> gameMapper){
        return obj->
                CustomerDto.builder()
                        .id(obj.getId())
                        .username(obj.getUsername())
                        .password(obj.getPassword())
                        .favoriteGames(obj.getFavoriteGames()
                                .stream()
                                .map(gameMapper::map)
                                .collect(Collectors.toSet()))
                        .build();

    }

    @Bean
    public Mapper<DeveloperStudioDto,DeveloperStudio> developerStudioMapper(){
        return obj->
                DeveloperStudio.builder()
                        .studioName(obj.getStudioName())
                        .creationAt(obj.getCreationAt())
                        .id(obj.getId())
                        .build();
    }

    @Bean
    public Mapper<DeveloperStudio,DeveloperStudioDto> developerStudioDtoMapper(){
        return obj->
                DeveloperStudioDto.builder()
                        .studioName(obj.getStudioName())
                        .creationAt(obj.getCreationAt())
                        .id(obj.getId())
                        .build();
    }

    @Bean
    public Mapper<Publisher,PublisherDto> publisherPublisherDtoMapper(){
        return obj->
                PublisherDto.builder()
                        .id(obj.getId())
                        .name(obj.getName())
                        .build();
    }
    @Bean
    public Mapper<PublisherDto,Publisher> publisherDtoPublisherMapper(){
        return obj->
                Publisher.builder()
                        .id(obj.getId())
                        .name(obj.getName())
                        .build();
    }

    @Bean
    public Mapper<Review,ReviewDto> reviewReviewDtoMapper(Mapper<Customer,CustomerDto> customerMapper,
                                                          Mapper<Comment,CommentDto> commentMapper,
                                                          Mapper<Game,GameDto> gameMapper){
        return obj->
                ReviewDto.builder()
                        .author(customerMapper.map(obj.getAuthor()))
                        .comments(obj.getComments()
                                .stream()
                                .map(commentMapper::map)
                                .collect(Collectors.toSet()))
                        .game(gameMapper.map(obj.getGame()))
                        .id(obj.getId())
                        .title(obj.getTitle())
                        .text(obj.getText())
                        .build();
    }
    @Bean
    public Mapper<ReviewDto,Review> reviewDtoReviewMapper(Mapper<CustomerDto,Customer> customerMapper,
                                                          Mapper<CommentDto,Comment> commentMapper,
                                                          Mapper<GameDto,Game> gameMapper){
        return obj->
                Review.builder()
                        .author(customerMapper.map(obj.getAuthor()))
                        .comments(obj.getComments()
                                .stream()
                                .map(commentMapper::map)
                                .collect(Collectors.toSet()))
                        .game(gameMapper.map(obj.getGame()))
                        .id(obj.getId())
                        .title(obj.getTitle())
                        .text(obj.getText())
                        .build();
    }
}


