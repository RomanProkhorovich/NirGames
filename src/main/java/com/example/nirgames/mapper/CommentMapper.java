package com.example.nirgames.mapper;


import com.example.nirgames.dto.CommentDto;
import com.example.nirgames.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface CommentMapper {

    Comment map(CommentDto dto);
    CommentDto map(Comment dto);
    List<Comment> mapToEntityList(List<CommentDto> dtos);
    List<CommentDto> mapToDtosList(List<Comment> games);
}
