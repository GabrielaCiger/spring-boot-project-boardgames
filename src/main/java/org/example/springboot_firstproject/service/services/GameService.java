package org.example.springboot_firstproject.service.services;

import fr.le_campus_numerique.square_games.engine.Game;
import org.example.springboot_firstproject.gametodatabase.TokenEntity;

import java.util.*;

public interface GameService {
    Game createGame(String gameIdentifier);
    Collection<Game> getGames();
    void addGame(Game game);
    boolean removeGame(String gameId);
    Optional<Game> getGame(String gameId);
}

