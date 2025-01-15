package org.example.springboot_firstproject.controller;

import fr.le_campus_numerique.square_games.engine.Game;
import org.example.springboot_firstproject.service.services.GameCreationParamsDTO;
import org.example.springboot_firstproject.service.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<?> createGame(@RequestBody @Validated GameCreationParamsDTO params) {
        try {
            gameService.addGame(params.getGameType());
            return ResponseEntity.status(HttpStatus.CREATED).body("Game created.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error: " + e.getMessage());
        }
    }

    @GetMapping
    public Collection<Game> getGames() {
        return gameService.getGames();
    }

    //TODO: add to Params to GET /

    @DeleteMapping("/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable String gameId) {
        try {
            boolean isDeleted = gameService.removeGame(gameId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Game successfully deleted.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found: " + gameId);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

    @GetMapping("/{gameId}")
    public Object getGame(@PathVariable String gameId) {
        try {
            Optional<Game> match = gameService.getGame(gameId);
            if (match.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(match.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found: " + gameId);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }

    }
}