package org.example.springboot_firstproject;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;
    private GameFactory gameFactory;
    private Collection<Game> games = new ArrayList<>();

    @GetMapping("/games")
    public Collection<String> getGamesIds(){ return gameCatalog.getGameIdentifiers(); }

    @PostMapping("/games")
    public String createGame(@RequestBody GameCreationParamsDTO params) {
        this.gameFactory = gameCatalog.getGameFactory(params.getGameType());
        Game newGame = gameFactory.createGame(params.getPlayerCount(), params.getBoardSize());
        games.add(newGame);
        return newGame.getId().toString();
    }

    @GetMapping("games/{gameId}")
    public Object getGame(@PathVariable String gameId) {
        return games.stream().filter(game -> game.getId().toString().equals(gameId)).findFirst();
    }

}
