package org.example.springboot_firstproject.service;

public class GameCreationParamsDTO {
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
