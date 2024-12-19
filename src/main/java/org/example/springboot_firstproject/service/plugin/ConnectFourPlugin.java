package org.example.springboot_firstproject.service.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.OptionalInt;

@Component
public class ConnectFourPlugin extends ConnectFourGameFactory implements GamePlugin {

    private final ConnectFourGameFactory gameFactory = new ConnectFourGameFactory();

    @Autowired
    private MessageSource messageSource;

    public ConnectFourPlugin() {}

    @Value("${game.connectfour.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.connectfour.default-board-size}")
    private int defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("messages.connect-four.title", null, locale);
    }
    @Override
    public Game createGame(OptionalInt playerCount, OptionalInt boardSize) {
        if (playerCount.isPresent() && boardSize.isPresent()) {
            return gameFactory.createGame(playerCount.getAsInt(), boardSize.getAsInt());
        }
        return gameFactory.createGame(defaultPlayerCount, defaultBoardSize);
    }
    @Override
    public String getGameIdentifier() {
        return "ConnectFour";
    }
}
