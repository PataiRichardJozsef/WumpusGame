package wumpus.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.exception.ReadMapException;
import wumpus.model.Hero;

@ExtendWith(MockitoExtension.class)
class MapReaderImplTest {

    private static final char[][] EXPECTED_MAP = {
        {'W', 'W', 'W', 'W', 'W', 'W'},
        {'W', '_', '_', '_', 'P', 'W'},
        {'W', 'U', 'G', 'P', '_', 'W'},
        {'W', '_', '_', '_', '_', 'W'},
        {'W', 'H', '_', 'P', '_', 'W'},
        {'W', 'W', 'W', 'W', 'W', 'W'}
    };
    private static final String TEST_HERO_NAME = "test-hero";
    private MapReaderImpl mapReader = new MapReaderImpl();

    @Test
    void readMap() {
        var hero = new Hero(TEST_HERO_NAME);

        var actual = mapReader.readMap(hero, "map/test_input1.txt");

        assertEquals(hero.getLookingDir(), "E");
        assertEquals(hero.getColPos(), 1);
        assertEquals(hero.getRowPos(), 4);
        assertTrue(Arrays.deepEquals(actual, EXPECTED_MAP));
    }

    @Test
    void readMap_validatesSize() {
        var hero = new Hero(TEST_HERO_NAME);

        assertThrows(ReadMapException.class, () -> mapReader.readMap(hero, "map/test_input2.txt"));
    }
    @Test
    void readMap_validatesCol() {
        var hero = new Hero(TEST_HERO_NAME);

        assertThrows(ReadMapException.class, () -> mapReader.readMap(hero, "map/test_input2.txt"));
    }
    @Test
    void readMap_validatesrow() {
        var hero = new Hero(TEST_HERO_NAME);

        assertThrows(ReadMapException.class, () -> mapReader.readMap(hero, "map/test_input3.txt"));
    }

    @Test
    void readMap_wumpusum() {
        var hero = new Hero(TEST_HERO_NAME);

        assertThrows(ReadMapException.class, () -> mapReader.readMap(hero, "map/test_input4.txt"));
    }
    @Test
    void readMap_goldnum() {
        var hero = new Hero(TEST_HERO_NAME);

        assertThrows(ReadMapException.class, () -> mapReader.readMap(hero, "map/test_input5.txt"));
    }
}