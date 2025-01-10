package org.example.springboot_firstproject.data.access;
import fr.le_campus_numerique.square_games.engine.Game;
import org.example.springboot_firstproject.gametodatabase.GameEntity;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InMemoryGameDao {

    private final List<Game> games = new ArrayList<>();

    public Collection<Game> findAll() {
        return Collections.unmodifiableCollection(games);
    }

    public Optional<Game> findById(String gameId) {
        return games.stream().filter(game -> game.getId().toString().equals(gameId)).findFirst();
    }

    public boolean addGame(Game game) {
        return games.add(game);
    }
    public boolean delete(String gameId) {
        return games.removeIf(game -> game.getId().toString().equals(gameId));
    }
}
