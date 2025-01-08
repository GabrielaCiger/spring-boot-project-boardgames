package org.example.springboot_firstproject.gametodatabase;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;
}
