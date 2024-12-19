package org.example.springboot_firstproject.service;
import java.util.Collection;
import java.util.Locale;

public interface GameCatalog {
    Collection<String> getGameIdentifiers(Locale locale);
}

