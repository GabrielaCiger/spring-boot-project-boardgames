package org.example.springboot_firstproject.service;

import org.example.springboot_firstproject.service.plugin.ConnectFourPlugin;
import org.example.springboot_firstproject.service.plugin.TaquinPlugin;
import org.example.springboot_firstproject.service.plugin.TicTacToePlugin;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameCatalogImpl implements GameCatalog {

    private final TicTacToePlugin ticTacToePlugin;
    private final ConnectFourPlugin connectFourPlugin;
    private final TaquinPlugin taquinPlugin;

    public GameCatalogImpl(TicTacToePlugin ticTacToePlugin, ConnectFourPlugin connectFourPlugin, TaquinPlugin taquinPlugin) {
        this.ticTacToePlugin = ticTacToePlugin;
        this.connectFourPlugin = connectFourPlugin;
        this.taquinPlugin = taquinPlugin;
    }

    @Override
    public Collection<String> getGameIdentifiers() {
        List<String> gameIdentifiers = new ArrayList<>();
        gameIdentifiers.add(ticTacToePlugin.getName(Locale.getDefault()));
        gameIdentifiers.add(connectFourPlugin.getName(Locale.getDefault()));
        gameIdentifiers.add(taquinPlugin.getName(Locale.getDefault()));
        return gameIdentifiers;
    }


}
