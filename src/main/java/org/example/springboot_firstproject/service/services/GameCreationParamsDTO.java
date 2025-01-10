package org.example.springboot_firstproject.service.services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GameCreationParamsDTO {
    @NotNull(message = "Game can't be null.")
    @NotBlank(message = "Game can't be blank.")
    private String gameType;

    public GameCreationParamsDTO(String gameType) {
        this.gameType = gameType;
    }

    public String getGameType() {
        return gameType;
    }
}
