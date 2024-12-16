package org.example.springboot_firstproject;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GameCatalogImpl implements GameCatalog {

    private final TicTacToeGameFactory ticTacToeGameFactory;
    private final ConnectFourGameFactory connectFourGameFactory;
    private final TaquinGameFactory taquinGameFactory;

    public GameCatalogImpl() {
        this.ticTacToeGameFactory = new TicTacToeGameFactory();
        this.connectFourGameFactory = new ConnectFourGameFactory();
        this.taquinGameFactory = new TaquinGameFactory();
    }

    @Override
    public Collection<String> getGameIdentifiers() {
        List<String> gameIdentifiers = new ArrayList<>();
        gameIdentifiers.add(ticTacToeGameFactory.getGameFactoryId());
        gameIdentifiers.add(connectFourGameFactory.getGameFactoryId());
        gameIdentifiers.add(taquinGameFactory.getGameFactoryId());
        return gameIdentifiers;
    }

    @Override
    public GameFactory getGameFactory(String gameIdentifier) {
        return switch (gameIdentifier) {
            case "tictactoe" -> ticTacToeGameFactory;
            case "connectfour" -> connectFourGameFactory;
            case "taquin" -> taquinGameFactory;
            default -> null;
        };
    }
}
