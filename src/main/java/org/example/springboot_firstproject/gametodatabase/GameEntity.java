package org.example.springboot_firstproject.gametodatabase;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class GameEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        private int boardSize;

        @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
        private List<PlayerEntity> players = new ArrayList<>();

        @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
        private List<TokenEntity> tokens = new ArrayList<>();

        @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
        private List<TokenEntity> remainingTokens = new ArrayList<>();

        private UUID currentPlayerId;

        public GameEntity() {}

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public int getBoardSize() {
                return boardSize;
        }

        public void setBoardSize(int boardSize) {
                this.boardSize = boardSize;
        }

        public List<PlayerEntity> getPlayers() {
                return players;
        }

        public void setPlayers(List<PlayerEntity> players) {
                this.players = players;
        }

        public List<TokenEntity> getTokens() {
                return tokens;
        }

        public void setTokens(List<TokenEntity> tokens) {
                this.tokens = tokens;
        }
}
