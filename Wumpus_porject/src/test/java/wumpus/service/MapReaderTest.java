package wumpus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.exception.ReadMapException;
import wumpus.model.hero.Hero;
import wumpus.model.state.GameState;
import wumpus.service.MapReader;

@ExtendWith(MockitoExtension.class)
class MapReaderTest {

    private static final char[][] EXPECTED_MAP = {
        {'W', 'W', 'W', 'W', 'W', 'W'},
        {'W', '_', '_', '_', 'P', 'W'},
        {'W', 'U', 'G', 'P', '_', 'W'},
        {'W', '_', '_', '_', '_', 'W'},
        {'W', '_', '_', 'P', '_', 'W'},
        {'W', 'W', 'W', 'W', 'W', 'W'}
    };
    private static final String TEST_HERO_NAME = "test-hero";
    private MapReader mapReader = new MapReader();

    private GameState gameState;

    @BeforeEach
    void init() {
        var hero = new Hero(TEST_HERO_NAME);
        gameState = new GameState();
        gameState.setHero(hero);
    }

    @Test
    void readMap() {
        mapReader.readMap(gameState, "map/test_input1.txt");

        assertEquals(gameState.getColumnPos(), 1);
        assertEquals(gameState.getColumnPos(), 1);
        assertTrue(Arrays.deepEquals(gameState.getMap(), EXPECTED_MAP));
    }

    @Test
    void readMap_validatesSize() {
        assertThrows(ReadMapException.class, () -> mapReader.readMap(gameState, "map/test_input2.txt"));
    }

    @Test
    void readMap_validatesCol() {
        assertThrows(ReadMapException.class, () -> mapReader.readMap(gameState, "map/test_input2.txt"));
    }

    @Test
    void readMap_validatesRow() {
        assertThrows(ReadMapException.class, () -> mapReader.readMap(gameState, "map/test_input3.txt"));
    }

    @Test
    void readMap_wumpusum() {
        assertThrows(ReadMapException.class, () -> mapReader.readMap(gameState, "map/test_input4.txt"));
    }

    @Test
    void readMap_goldnum() {
        assertThrows(ReadMapException.class, () -> mapReader.readMap(gameState, "map/test_input5.txt"));
    }

}