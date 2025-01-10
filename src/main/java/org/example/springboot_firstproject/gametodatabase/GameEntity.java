package org.example.springboot_firstproject.gametodatabase;

import fr.le_campus_numerique.square_games.engine.GameStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class GameEntity {

        @Id
        private UUID id;

        private @NotNull String factoryId;
        private @Positive int boardSize;
        private @NotNull GameStatus gameStatus;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        private List<PlayerEntity> players = new ArrayList<>();

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        private List<TokenEntity> remainingTokens = new ArrayList<>();

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        private List<TokenEntity> removedTokens = new ArrayList<>();

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
        public List<UUID> getPlayerIds() {
                List<UUID> playerIds = new ArrayList<>();
                for (PlayerEntity player : players) {
                        playerIds.add(player.getId());
                }
                return playerIds;
        }

        public void setPlayers(List<PlayerEntity> players) {
                this.players = players;
        }

        public List<TokenEntity> getRemainingTokens() {
                return remainingTokens;
        }

        public void setRemainingTokens(List<TokenEntity> tokens) {
                this.remainingTokens = tokens;
        }
        public List<TokenEntity> getRemovedTokens() {
                return removedTokens;
        }

        public void setRemovedTokens(List<TokenEntity> removedTokens) {
                this.removedTokens = removedTokens;
        }
        public UUID getCurrentPlayerId() {
                return currentPlayerId;
        }
        public void setCurrentPlayerId(UUID currentPlayerId) {
                this.currentPlayerId = currentPlayerId;
        }

        public @NotNull GameStatus getGameStatus() {
                return gameStatus;
        }

        public void setGameStatus(@NotNull GameStatus gameStatus) {
                this.gameStatus = gameStatus;
        }
}
