package org.example.springboot_firstproject;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;
    private GameFactory gameFactory;

    @GetMapping("/games")
    public Collection<String> getGamesIds(){ return gameCatalog.getGameIdentifiers(); }

    @PostMapping("/games")
    public String createGame(@RequestBody GameCreationParamsDTO params) {
        this.gameFactory = gameCatalog.getGameFactory(params.getGameType());
        Game game = gameFactory.createGame(params.getPlayerCount(), params.getBoardSize());
        return game.getId().toString();
    }

    @GetMapping("games/{gameID}")
    public Object getGame(@PathVariable String gameId) {
        // TODO - actually get and return game with id 'gameId'
        return null;
    }

}
