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
import org.springframework.validation.annotation.Validated;
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

    @GetMapping()
    public Collection<String> getGamesIds(){ return gameCatalog.getGameIdentifiers(); }
    @GetMapping("/all")
    public Collection<Game> getGames(){ return gameCatalog.getGames(); }

    @PostMapping("/create")
    public ResponseEntity<?> createGame(@RequestBody @Validated GameCreationParamsDTO params) {
        try {
            Game newGame = gameService.createGame(params.getGameType());
            gameCatalog.addGame(newGame);
            return ResponseEntity.status(HttpStatus.CREATED).body("Game created.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

    @GetMapping("/{gameId}")
    public Object getGame(@PathVariable String gameId) {
        return gameCatalog.getGames().stream().filter(game -> game.getId().toString().equals(gameId)).findFirst();
    }

    @GetMapping("/ongoing")
    public List<Map<String, String>> getOngoingGames() {
        return gameCatalog.getGames().stream()
                .filter(game -> game.getStatus() == GameStatus.ONGOING)
                .map(game -> Map.of("id", game.getId().toString(), "game", game.getFactoryId()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable String gameId) {
        try {
            boolean isDeleted = gameCatalog.removeGame(gameId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Game successfully deleted : " + gameId);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found: " + gameId);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }




}
