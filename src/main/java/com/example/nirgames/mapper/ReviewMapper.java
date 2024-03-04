package com.example.nirgames.mapper;

import com.example.nirgames.dto.PublisherDto;
import com.example.nirgames.dto.ReviewDto;
import com.example.nirgames.model.Publisher;
import com.example.nirgames.model.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GameMapper.class,
        CustomerMapper.class,
        CommentMapper.class})
public interface ReviewMapper {

    Review map(ReviewDto dto);
    ReviewDto map(Review dto);
    List<Review> mapToEntityList(List<ReviewDto> dtos);
    List<ReviewDto> mapToDtosList(List<Review> games);
}
