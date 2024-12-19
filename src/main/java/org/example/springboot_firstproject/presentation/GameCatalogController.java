package org.example.springboot_firstproject.presentation;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import org.apache.tomcat.util.http.parser.AcceptLanguage;
import org.example.springboot_firstproject.service.GameCatalog;
import org.example.springboot_firstproject.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping()
    public Collection<String> getGamesIds(@RequestHeader(name = "Accept-Language") Locale locale) {
        return gameCatalog.getGameIdentifiers(locale);
    }


}