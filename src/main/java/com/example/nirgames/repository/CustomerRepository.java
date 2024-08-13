package com.example.nirgames.repository;

import com.example.nirgames.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUsername(String username);
    boolean existsCustomersByUsername(String username);
    @Modifying
    @Query(nativeQuery = true, value = "insert into customer_games (customer_id, games_id) values (?1, ?2)")
    void insertFavoriteGame(Long customerId, Long game_id);
}
