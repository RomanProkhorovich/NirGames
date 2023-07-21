package com.example.nirgames.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperStudio that = (DeveloperStudio) o;
        return studioName.equals(that.studioName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studioName);
    }
}
