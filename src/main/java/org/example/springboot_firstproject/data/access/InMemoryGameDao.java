package org.example.springboot_firstproject.data.access;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InMemoryGameDao implements GameDao {

    private final List<Game> games = new ArrayList<>();

    @Override
    public Collection<Game> findAll() {
        return Collections.unmodifiableCollection(games);
    }

    @Override
    public Optional<Game> findById(String gameId) {
        return games.stream().filter(game -> game.getId().toString().equals(gameId)).findFirst();
    }

    @Override
    public boolean addGame(Game game) {
        return games.add(game);
    }

    @Override
    public boolean delete(String gameId) {
        return games.removeIf(game -> game.getId().toString().equals(gameId));
    }
}
