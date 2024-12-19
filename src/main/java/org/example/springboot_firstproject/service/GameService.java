package org.example.springboot_firstproject.service;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GameService {
    Game createGame(String gameIdentifier);
    Collection<Game> getGames();
    boolean addGame(Game game);
    boolean removeGame(String gameId);
    Optional<Game> getGame(String gameId);
    List<Map<String, String>> getOngoingGames();
}

