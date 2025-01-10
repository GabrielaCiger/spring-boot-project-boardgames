package org.example.springboot_firstproject.service.services;

import fr.le_campus_numerique.square_games.engine.Game;
import jakarta.transaction.Transactional;
import org.example.springboot_firstproject.data.access.GameDao;
import org.example.springboot_firstproject.gametodatabase.*;
import org.example.springboot_firstproject.service.plugin.GamePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    GameDao gameDao;

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

    @Override
    public Collection<Game> getGames() {
        return gameDao.findAll();
    }

    @Override
    public void addGame(String gameIdentifier) {
        gameDao.addGame(createGame(gameIdentifier));
    }

    @Override
    public boolean removeGame(String gameId) {
        return gameDao.delete(gameId);
    }

    @Override
    public Optional<Game> getGame(String gameId) {
        return gameDao.findById(gameId);
    }

}

