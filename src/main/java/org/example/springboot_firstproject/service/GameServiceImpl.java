package org.example.springboot_firstproject.service;

import fr.le_campus_numerique.square_games.engine.Game;
import org.example.springboot_firstproject.service.plugin.ConnectFourPlugin;
import org.example.springboot_firstproject.service.plugin.TaquinPlugin;
import org.example.springboot_firstproject.service.plugin.TicTacToePlugin;
import org.springframework.stereotype.Service;

import java.util.OptionalInt;

@Service
public class GameServiceImpl implements GameService {

    private final TicTacToePlugin ticTacToePlugin;
    private final ConnectFourPlugin connectFourPlugin;
    private final TaquinPlugin taquinPlugin;

    public GameServiceImpl(TicTacToePlugin ticTacToePlugin, ConnectFourPlugin connectFourPlugin, TaquinPlugin taquinPlugin) {
        this.ticTacToePlugin = ticTacToePlugin;
        this.connectFourPlugin = connectFourPlugin;
        this.taquinPlugin = taquinPlugin;
    }
    @Override
    public Game createGame(String gameIdentifier) {
        switch (gameIdentifier) {
            case "TicTacToe": ticTacToePlugin.createGame(OptionalInt.empty(), OptionalInt.empty());
            case "ConnectFour" : connectFourPlugin.createGame(OptionalInt.empty(), OptionalInt.empty());
            case "Taquin":  taquinPlugin.createGame(OptionalInt.empty(), OptionalInt.empty());
            default: return null;
        }
    }
}
