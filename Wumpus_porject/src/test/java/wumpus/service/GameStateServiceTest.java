package wumpus.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static wumpus.TestConstants.USERNAME;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.model.hero.Actions;
import wumpus.model.hero.Hero;
import wumpus.model.hero.PlayerDirection;
import wumpus.model.state.GameState;

@ExtendWith(MockitoExtension.class)
class GameStateServiceTest {

    @Mock
    MapReader mapReaderMock;

    MapReader mapReader = new MapReader();
    GameState testGameState;

    @BeforeEach
    void initGameState() {
        var hero = new Hero(USERNAME);
        testGameState = new GameState();
        testGameState.setHero(hero);
        mapReader.readMap(testGameState, "map/test_input1.txt");
    }

    @InjectMocks
    GameStateService gameStateService;

    @Test
    void init() {
        gameStateService.init(new GameState());

        verify(mapReaderMock, times(1)).readMap(any());
    }

    @Test
    void execute_step() {
        gameStateService.execute(testGameState, Actions.STEP, null);

        assertThat(testGameState.getRowPos()).isEqualTo(4);
        assertThat(testGameState.getColumnPos()).isEqualTo(2);
    }

    @Test
    void execute_rotate_left() {
        gameStateService.execute(testGameState, Actions.ROTATE, "L");

        assertThat(testGameState.getHero().getDirection()).isEqualTo(PlayerDirection.NORTH);
    }

    @Test
    void execute_rotate_right() {
        gameStateService.execute(testGameState, Actions.ROTATE, "R");

        assertThat(testGameState.getHero().getDirection()).isEqualTo(PlayerDirection.SOUTH);
    }

    @Test
    void execute_collect() {
        testGameState.setColumnPos(2);
        testGameState.setRowPos(2);
        gameStateService.execute(testGameState, Actions.COLLECT, null);

        assertThat(testGameState.getHero().getHasGold()).isEqualTo(true);
    }

    @Test
    void execute_shoot_when_hits_wumpus() {
        testGameState.getHero().setDirection(PlayerDirection.NORTH);
        gameStateService.execute(testGameState, Actions.SHOOT, null);

        assertThat(testGameState.getMap()[2][1]).isEqualTo('_');
        assertThat(testGameState.getHero().getNumberOfArrows()).isEqualTo(0);
    }

    @Test
    void execute_shoot_when_hits_wall() {
        gameStateService.execute(testGameState, Actions.SHOOT, null);

        assertThat(testGameState.getHero().getNumberOfArrows()).isEqualTo(0);
    }

    @Test
    void execute_shoot_when_no_more_arrows() {
        testGameState.getHero().setNumberOfArrows(0);

        var actual = gameStateService.execute(testGameState, Actions.SHOOT, null);

        assertThat(actual).isEqualTo('I');
    }



}
