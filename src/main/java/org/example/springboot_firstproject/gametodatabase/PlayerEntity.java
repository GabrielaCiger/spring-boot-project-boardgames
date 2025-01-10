package org.example.springboot_firstproject.gametodatabase;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PlayerEntity {

    @Id
    private UUID id;

    @ManyToOne
    private GameEntity game;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }
}
