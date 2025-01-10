package org.example.springboot_firstproject.gametodatabase;

import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.example.springboot_firstproject.data.access.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

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
        List<GameEntity> gameEntities = gameRepository.findAll();
        List<Game> games = new ArrayList<>();

        for (GameEntity gameEntity : gameEntities) {
            try {
                Game game = createGameFromGameEntity(gameEntity);
                games.add(game);
            } catch (InconsistentGameDefinitionException e) {
                throw new RuntimeException(e);
            }
        }

        return games;
    }

    @Override
    public Optional<Game> findById(String gameId) {
        return null;
    }

    @Override
    public GameEntity addGame(Game game) {
        GameEntity entity = createGameEntity(game);
        return gameRepository.save(entity);
    }

    @Override
    public boolean delete(String gameId) {
        return false;
    }

    private GameEntity createGameEntity(Game game) {
        // * GAME
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(game.getId());
        gameEntity.setBoardSize(game.getBoardSize());
        gameEntity.setFactoryId(game.getFactoryId());
        gameEntity.setGameStatus(game.getStatus());

        // * PLAYERS
        List<UUID> playerIds = new ArrayList<>(game.getPlayerIds());
        List<PlayerEntity> players = new ArrayList<>();

        for (int i = 0; i < game.getPlayerIds().size(); i++) {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setGame(gameEntity);
            playerEntity.setId(playerIds.get(i));
            players.add(playerEntity);
        }
        gameEntity.setPlayers(players);
        gameEntity.setCurrentPlayerId(game.getCurrentPlayerId());

        // * REMAINING TOKENS
        List<Token> tokens = new ArrayList<>(game.getRemainingTokens());
        List<TokenEntity> tokenEntities = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            tokenEntities.add(createTokenEntity(tokens.get(i)));
        }
        gameEntity.setRemainingTokens(tokenEntities);

        // * REMOVED TOKENS
        List<Token> removedTokens = new ArrayList<>(game.getRemovedTokens());
        List<TokenEntity> removedTokenEntities = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            if (removedTokens.contains(tokens.get(i))) {
                removedTokenEntities.add(createTokenEntity(removedTokens.get(i)));
            }
        }
        gameEntity.setRemovedTokens(removedTokenEntities);

        return gameEntity;
    }

    private TokenEntity createTokenEntity(Token token) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setId(UUID.randomUUID());
        tokenEntity.setOwnerId(token.getOwnerId().orElse(null));
        if (token.getPosition() != null) {
            tokenEntity.setX(token.getPosition().x());
            tokenEntity.setY(token.getPosition().y());
        }
        return tokenEntity;
    }

    public Game createGameFromGameEntity(GameEntity gameEntity) throws InconsistentGameDefinitionException {
        String factoryId = gameEntity.getFactoryId();
        GameFactory gameFactory = getGameFactory(factoryId);
        assert gameFactory != null;
        return gameFactory.createGameWithIds(
                gameEntity.getId(),
                gameEntity.getBoardSize(),
                gameEntity.getPlayerIds(),
                mapTokens(gameEntity.getRemainingTokens()),
                mapTokens(gameEntity.getRemovedTokens())
        );
    }

    private GameFactory getGameFactory(String factoryId) {
        try {
            switch (factoryId) {
                case "tictactoe" -> {
                    return new TicTacToeGameFactory();
                }
                case "15 puzzle" -> {
                    return new TaquinGameFactory();
                }
                case "connect4" -> {
                    return new ConnectFourGameFactory();
                }
        }
        } catch (Exception e) {
            throw new RuntimeException("Invalid game ID.", e);
        }
        return null;
    }
    private static TokenPosition<UUID> mapToken(TokenEntity tokenEntity) {
        try {
            assert tokenEntity.getOwnerId() != null;
            return new TokenPosition<>(UUID.fromString(tokenEntity.getOwnerId().toString()),
                    tokenEntity.getName(),
                    tokenEntity.getX(),
                    tokenEntity.getY());
        } catch (Exception e) {
            throw new RuntimeException("Invalid token.", e);
        }
    }

    private static Collection<TokenPosition<UUID>> mapTokens(Collection<TokenEntity> tokenEntities) {
        Collection<TokenPosition<UUID>> tokenPositions = new ArrayList<>();
        for (TokenEntity tokenEntity : tokenEntities) {
            tokenPositions.add(mapToken(tokenEntity));
        }
        return tokenPositions;
    }

}
