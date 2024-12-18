package org.example.springboot_firstproject.presentation;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.example.springboot_firstproject.service.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping()
    public Collection<String> getGamesIds(){ return gameCatalog.getGameIdentifiers(); }
    @GetMapping("/all")
    public Collection<Game> getGames(){ return gameCatalog.getGames(); }

    @GetMapping("/ongoing")
    public List<Map<String, String>> getOngoingGames() {
        return gameCatalog.getGames().stream()
                .filter(game -> game.getStatus() == GameStatus.ONGOING)
                .map(game -> Map.of("id", game.getId().toString(), "game", game.getFactoryId(), "status", game.getStatus().toString()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable String gameId) {
        try {
            boolean isDeleted = gameCatalog.removeGame(gameId);
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
            Optional<Game> match = gameCatalog.getGames().stream().filter(game -> game.getId().toString().equals(gameId)).findFirst();
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
