package com.example.nirgames.repository;

import com.example.nirgames.model.Comment;
import com.example.nirgames.model.Customer;
import com.example.nirgames.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Set<Comment> findAllByCustomer (Customer customer);
}
