package com.example.nirgames.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    private String firstname;
    private String lastname;

    @NonNull
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Role role;

    @NonNull
    @Column(unique = true,nullable = false)
    private String username;

    @ManyToMany
    @JoinTable(name = "customer_games",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "games_id"))
    private Set<Game> favoriteGames = new LinkedHashSet<>();

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
