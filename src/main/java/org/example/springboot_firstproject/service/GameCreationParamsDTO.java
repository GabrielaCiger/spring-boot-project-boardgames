package org.example.springboot_firstproject.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GameCreationParamsDTO {
    @NotNull(message = "Game can't be null.")
    @NotBlank(message = "Game can't be blank.")
    private String gameType;

    private int boardSize;
    private int playerCount;

    public GameCreationParamsDTO(String gameType, int boardSize, int playerCount) {
        this.gameType = gameType;
        this.boardSize = boardSize;
        this.playerCount = playerCount;
    }

    public String getGameType() {
        return gameType;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}
