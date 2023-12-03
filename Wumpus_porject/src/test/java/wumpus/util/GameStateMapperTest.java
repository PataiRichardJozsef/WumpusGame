package wumpus.util;

import static org.assertj.core.api.Assertions.assertThat;
import static wumpus.TestConstants.COLUMN_POSITION;
import static wumpus.TestConstants.GAME_STATE;
import static wumpus.TestConstants.HAS_GOLD;
import static wumpus.TestConstants.NUMBER_OF_ARROWS;
import static wumpus.TestConstants.NUMBER_OF_STEPS;
import static wumpus.TestConstants.PLAYER_DIRECTION;
import static wumpus.TestConstants.PLAYER_DIRECTION_STRING;
import static wumpus.TestConstants.ROW_POSITION;
import static wumpus.TestConstants.START_COLUMN_POSITION;
import static wumpus.TestConstants.START_ROW_POSITION;
import static wumpus.TestConstants.USERNAME;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import wumpus.entity.Game;
import wumpus.model.hero.Hero;
import wumpus.model.state.GameState;

class GameStateMapperTest {

    @Test
    void buildGamaEntityFromState() {
        var expectedEntity = dummyEntity();

        var actual = GameStateMapper.buildGamaEntityFromState(dummyGameState(), USERNAME);

        assertThat(actual).isEqualTo(expectedEntity);
    }

    @Test
    void buildGameStateFromEntity() {
        var expectedGameState = dummyGameState();

        var actual = GameStateMapper.buildGamaStateFromEntity(dummyEntity());

        assertThat(actual).isEqualTo(expectedGameState);
    }

    private Game dummyEntity() {
        return Game.builder()
            .numberOfSteps(NUMBER_OF_STEPS)
            .hasGold(HAS_GOLD)
            .startRowPosition(START_ROW_POSITION)
            .startColumnPosition(START_COLUMN_POSITION)
            .rowPosition(ROW_POSITION)
            .columnPosition(COLUMN_POSITION)
            .numberOfArrows(NUMBER_OF_ARROWS)
            .direction(PLAYER_DIRECTION_STRING)
            .username(USERNAME)
            .gameState(GAME_STATE)
            .build();
    }

    private GameState dummyGameState() {
        var hero = new Hero(USERNAME, NUMBER_OF_ARROWS, PLAYER_DIRECTION, HAS_GOLD);
        return GameState.builder()
            .numberOfSteps(NUMBER_OF_STEPS)
            .hero(hero)
            .map(GameStateSerializer.deserialize(GAME_STATE))
            .rowPos(ROW_POSITION)
            .columnPos(COLUMN_POSITION)
            .startPosition(Pair.of(START_COLUMN_POSITION, START_ROW_POSITION))
            .build();
    }
}
