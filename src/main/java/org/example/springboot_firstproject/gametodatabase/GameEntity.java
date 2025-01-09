package org.example.springboot_firstproject.gametodatabase;

import fr.le_campus_numerique.square_games.engine.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class GameEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        private @NotNull String factoryId;
        private @Positive int boardSize;

        @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<PlayerEntity> players = new ArrayList<>();

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        private List<TokenEntity> tokens = new ArrayList<>();

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        private List<TokenEntity> remainingTokens = new ArrayList<>();

        private UUID currentPlayerId;

        public GameEntity() {}

        public @NotNull String getFactoryId() {
                return factoryId;
        }

        public void setFactoryId(@NotNull String factoryId) {
                this.factoryId = factoryId;
        }

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
        public List<TokenEntity> getRemainingTokens() {
                return remainingTokens;
        }
        public void setRemainingTokens(List<TokenEntity> remainingTokens) {
                this.remainingTokens = remainingTokens;
        }
        public UUID getCurrentPlayerId() {
                return currentPlayerId;
        }
        public void setCurrentPlayerId(UUID currentPlayerId) {
                this.currentPlayerId = currentPlayerId;
        }
}
