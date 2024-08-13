package com.example.nirgames.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(unique = true,nullable = false)
    private String title;

    @Column(nullable = false, length = 10000)
    private String text;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "game_id")
    private Game game;

    private Integer score;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Customer author;

    @Builder.Default
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "review_id")
    private Set<Comment> comments = new LinkedHashSet<>();

    @Builder.Default
    private Date createdAt = new Date();


    public void addComment(Comment comment){
        var a = new LinkedHashSet<>(comments);
        a.add(comment);
        this.setComments(a);
    }
}
