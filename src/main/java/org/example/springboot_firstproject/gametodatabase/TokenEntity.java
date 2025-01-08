package org.example.springboot_firstproject.gametodatabase;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    private int x;
    private int y;

    private UUID ownerId;

    @Embedded
    private Position position;

    private String name;

    @ElementCollection
    private List<Position> allowedMoves = new ArrayList<>();
}
