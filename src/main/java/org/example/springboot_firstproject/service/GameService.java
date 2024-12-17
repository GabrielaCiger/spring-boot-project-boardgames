package org.example.springboot_firstproject.service;

import fr.le_campus_numerique.square_games.engine.Game;

public interface GameService {
    Game createGame(String gameIdentifier);
}
