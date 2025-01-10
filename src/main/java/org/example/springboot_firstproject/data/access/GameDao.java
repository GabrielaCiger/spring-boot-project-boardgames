package org.example.springboot_firstproject.data.access;

import fr.le_campus_numerique.square_games.engine.Game;
import jakarta.validation.constraints.NotNull;
import org.example.springboot_firstproject.gametodatabase.GameEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface GameDao {
    @NotNull
    Collection<Game> findAll();
    Optional<Game> findById(@NotNull String gameId);
    GameEntity addGame(@NotNull Game game);
    boolean delete(@NotNull String gameId);
}
