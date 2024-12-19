package org.example.springboot_firstproject.service;

import org.example.springboot_firstproject.service.plugin.GamePlugin;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class GameCatalogImpl implements GameCatalog {

    private final List<GamePlugin> gamePlugins;

    public GameCatalogImpl(List<GamePlugin> gamePlugins) {
        this.gamePlugins = gamePlugins;
    }

    @Override
    public Collection<String> getGameIdentifiers(Locale locale) {
        List<String> gameIdentifiers = new ArrayList<>();
        for (GamePlugin plugin : gamePlugins) {
                gameIdentifiers.add(plugin.getName(locale));
        }
        return gameIdentifiers;
    }
}
