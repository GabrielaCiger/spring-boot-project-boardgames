package org.example.springboot_firstproject.gametodatabase;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import org.example.springboot_firstproject.data.access.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class JpaGameDao implements GameDao {

    @Autowired
    GameRepository gameRepository;

    public JpaGameDao(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Collection<Game> findAll() {
        return List.of();
    }

    @Override
    public Optional<Game> findById(String gameId) {
        return Optional.empty();
    }

    @Override
    public boolean addGame(Game game) {
        return false;
    }

    @Override
    public boolean delete(String gameId) {
        return false;
    }

 private GameEntity createGameEntity(Game game) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(game.getId());
        gameEntity.setBoardSize(game.getBoardSize());
        gameEntity.setFactoryId(game.getFactoryId());

        PlayerEntity player1 = new PlayerEntity();
        player1.setGame(gameEntity);
        player1.setId(gameEntity.getPlayers().getFirst().getId());
        player1.setName(gameEntity.getPlayers().getFirst().getName());

        PlayerEntity player2 = new PlayerEntity();
        player2.setGame(gameEntity);
        player2.setId(gameEntity.getPlayers().get(1).getId());
        player2.setName(gameEntity.getPlayers().get(1).getName());

        List<PlayerEntity> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        gameEntity.setPlayers(players);




        return gameEntity;
 }

 private TokenEntity createTokenEntity(Token token) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setName(token.getName());
        tokenEntity.setOwnerId(token.getOwnerId().isPresent() ? token.getOwnerId().get() : null);
        tokenEntity.setX(token.getPosition().x());
        tokenEntity.setY(token.getPosition().y());
        return tokenEntity;
 }

}
