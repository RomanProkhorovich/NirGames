package com.example.nirgames.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(unique = true,nullable = false)
    private String username;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Set<Game> favoriteGames = new LinkedHashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE)
    private Set<Comment> comments = new LinkedHashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return username.equals(customer.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }


}
