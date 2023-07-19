package com.example.nirgames.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DeveloperStudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(unique = true, nullable = false)
    private String studioName;
    private Year creationAt;

    public DeveloperStudio(@NonNull String studioName, Year creationAt) {
        this.studioName = studioName;
        this.creationAt = creationAt;
    }
}
