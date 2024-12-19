package org.example.springboot_firstproject.service;

import fr.le_campus_numerique.square_games.engine.Game;
import org.example.springboot_firstproject.service.plugin.GamePlugin;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

@Service
public class GameServiceImpl implements GameService {

    private final Map<String, GamePlugin> gamePlugins = new HashMap<>();

    public GameServiceImpl(List<GamePlugin> plugins) {
        for (GamePlugin plugin : plugins) {
            gamePlugins.put(plugin.getGameIdentifier(), plugin);
        }
    }
    @Override
    public Game createGame(String gameIdentifier) {
        GamePlugin plugin = gamePlugins.get(gameIdentifier);
        if (plugin == null) {
            throw new IllegalArgumentException("Unknown game: " + gameIdentifier);
        }
        return plugin.createGame(OptionalInt.empty(), OptionalInt.empty());
    }
}
