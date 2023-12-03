package wumpus.util;

import static wumpus.util.GameStateSerializer.serialize;

import org.apache.commons.lang3.tuple.Pair;
import wumpus.entity.Game;
import wumpus.model.hero.Hero;
import wumpus.model.hero.PlayerDirection;
import wumpus.model.state.GameState;

public final class GameStateMapper {

    private GameStateMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Game buildGamaEntityFromState(GameState gameState, String userName) {
        return Game.builder()
            .username(userName)
            .columnPosition(gameState.getColumnPos())
            .rowPosition(gameState.getRowPos())
            .direction(gameState.getHero().getDirection().toString())
            .gameState(serialize(gameState.getMap()))
            .numberOfArrows(gameState.getHero().getNumberOfArrows())
            .startColumnPosition(gameState.getStartPosition().getLeft())
            .startRowPosition(gameState.getStartPosition().getRight())
            .hasGold(gameState.getHero().getHasGold())
            .numberOfSteps(gameState.getNumberOfSteps())
            .build();
    }

    public static GameState buildGamaStateFromEntity(Game game) {
        var hero = new Hero(game.getUsername(), game.getNumberOfArrows(), PlayerDirection.valueOf(game.getDirection()), game.getHasGold());

        return GameState.builder()
            .columnPos(game.getColumnPosition())
            .rowPos(game.getRowPosition())
            .map(GameStateSerializer.deserialize(game.getGameState()))
            .hero(hero)
            .numberOfSteps(game.getNumberOfSteps())
            .startPosition(Pair.of(game.getStartColumnPosition(),game.getStartRowPosition()))
            .build();
    }
}
