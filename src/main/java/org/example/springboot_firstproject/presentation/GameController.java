package org.example.springboot_firstproject.presentation;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.example.springboot_firstproject.service.GameCatalog;
import org.example.springboot_firstproject.service.GameCreationParamsDTO;
import org.example.springboot_firstproject.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameCatalog gameCatalog;

    @Autowired
    private GameService gameService;

    private final Collection<Game> games = new ArrayList<>();

    @GetMapping()
    public Collection<String> getGamesIds(){ return gameCatalog.getGameIdentifiers(); }

    @PostMapping()
    public String createGame(@RequestBody GameCreationParamsDTO params) {
        try {
            Game newGame = gameService.createGame(params.getGameType());
            games.add(newGame);
            return newGame.getId().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/{gameId}")
    public Object getGame(@PathVariable String gameId) {
        return games.stream().filter(game -> game.getId().toString().equals(gameId)).findFirst();
    }

    @GetMapping("/ongoing")
    public List<Map<String, String>> getOngoingGames() {
        return games.stream()
                .filter(game -> game.getStatus() == GameStatus.ONGOING)
                .map(game -> Map.of("id", game.getId().toString(), "game", game.getFactoryId()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable String gameId) {
        boolean isDeleted = games.removeIf(game -> game.getId().toString().equals(gameId));
        if (isDeleted) {
            return ResponseEntity.ok("Game deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) //404
                    .body("Game with ID " + gameId + " not found");
        }
    }




}