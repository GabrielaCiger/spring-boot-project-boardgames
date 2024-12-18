package org.example.springboot_firstproject.service;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;

public interface GameCatalog {
    Collection<String> getGameIdentifiers();
    Collection<Game> getGames();
    void addGame(Game game);
    boolean removeGame(String gameId);
}
