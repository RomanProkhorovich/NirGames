package com.example.nirgames.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SystemRequries {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer RAM;
    private Integer ROM;
    private String CPU;
    private String GPU;



}
