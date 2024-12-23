package org.example.springboot_firstproject.presentation;
import org.example.springboot_firstproject.service.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/games")
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping()
    public Collection<String> getGamesIds(@RequestHeader(name = "Accept-Language", required = false, defaultValue = "fr-FR") Locale locale) {
        return gameCatalog.getGameIdentifiers(locale);
    }


}