package wumpus.service;

import static wumpus.model.state.GameState.EMPTY_PLACE;
import static wumpus.model.state.GameState.HERO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wumpus.exception.ReadMapException;
import wumpus.model.hero.PlayerDirection;
import wumpus.model.state.GameState;

public class MapReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapReader.class);

    public GameState readMap(GameState state) {
        return readMap(state, "map/wumpluszinput.txt");
    }

    @SneakyThrows
    public GameState readMap(GameState state, String path) {
        String line;
        int size;
        int col;
        int row;
        String position;

        int wumpusSize = 0;
        int goldSize = 0;

        char[][] map;

        InputStream inputStream = MapReader.class.getClassLoader().getResourceAsStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        //Első sor beolvasása
        line = bufferedReader.readLine();
        String[] firstLine = line.split("\\s+");
        size = Integer.valueOf(firstLine[0]);
        col = ((int) firstLine[1].charAt(0)) - 65;
        row = Integer.valueOf(firstLine[2]) - 1; // fájl első sorba 1 től indexeli
        position = firstLine[3];

        state.setColumnPos(col);
        state.getHero().setDirection(PlayerDirection.from(position));
        state.setRowPos(row);
        state.setStartPosition(Pair.of(row, col));

        // tömb inicializálása az első sorba beolvasott méretre
        map = new char[size][size];
        int i = 0;

        if (size < 6 || size > 20) {
            throw new ReadMapException("A pálya mérete 6 és 20 között lehet!");
        }

        while ((line = bufferedReader.readLine()) != null) {

            if (line.length() != size) {
                throw new ReadMapException("Nem megfelelő a pálya sorok hossza!");
            }

            if (i > size - 1) {
                throw new ReadMapException("Nem megfelelő a pálya oszlopok hossza!");
            }

            for (int j = 0; j < map.length; j++) {
                char fieldValue = line.charAt(j);
                if (fieldValue == HERO) {
                    fieldValue = EMPTY_PLACE;
                }

                map[i][j] = fieldValue;

                if ('U' == fieldValue) {
                    wumpusSize++;
                } else if ('G' == fieldValue) {
                    goldSize++;
                }
            }

            i++;
        }

        if (goldSize > 1) {
            throw new ReadMapException("Túl sok arany van a pályán!");
        }

        if (size <= 8 && wumpusSize > 1) {
            throw new ReadMapException("Túl sok wumpus található a pályán!");
        } else if (size <= 9 && size >= 14 && wumpusSize > 2) {
            throw new ReadMapException("Túl sok wumpus található a pályán!");
        } else if (size > 14 && wumpusSize != 3) {
            throw new ReadMapException("Túl sok wumpus található a pályán!");
        }

        LOGGER.info("Wumpus size: {}", wumpusSize);
        LOGGER.info("Gold size: {}", goldSize);
        state.setMap(map);
        state.getHero().setNumberOfArrows(wumpusSize);
        return state;
    }

}
