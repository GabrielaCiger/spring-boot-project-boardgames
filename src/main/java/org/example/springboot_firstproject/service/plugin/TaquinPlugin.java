package org.example.springboot_firstproject.service.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.OptionalInt;

@Component
public class TaquinPlugin implements GamePlugin {

    private final TaquinGameFactory gameFactory = new TaquinGameFactory();

    @Autowired
    private MessageSource messageSource;

    public TaquinPlugin() {}

    @Value("${game.taquin.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.taquin.default-board-size}")
    private int defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.taquin.title", null, locale);
    }
    @Override
    public Game createGame(OptionalInt playerCount, OptionalInt boardSize) {
        return gameFactory.createGame(defaultPlayerCount, defaultBoardSize);
    }
}
