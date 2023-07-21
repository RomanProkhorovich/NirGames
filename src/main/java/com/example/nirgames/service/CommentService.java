package com.example.nirgames.service;

import com.example.nirgames.model.Comment;
import com.example.nirgames.model.Customer;
import com.example.nirgames.model.Game;
import com.example.nirgames.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Optional<Comment> findById(Long id){
        return commentRepository.findById(id);
    }
    public Set<Comment> findAllByCustomer(Customer customer){
        return commentRepository.findAllByCustomer(customer);
    }
    public List<Comment> findAll(){
        return commentRepository.findAll();
    }
    public Set<Comment> findByGame(Game game){
        return commentRepository.findAllByGame(game);
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }
    public void delete (Long id){
        commentRepository.deleteById(id);
    }
}
