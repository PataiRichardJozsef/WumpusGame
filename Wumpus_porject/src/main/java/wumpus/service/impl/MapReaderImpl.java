package wumpus.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wumpus.exception.ReadMapException;
import wumpus.model.Hero;
import wumpus.service.MapReader;

public class MapReaderImpl implements MapReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapReaderImpl.class);

    @Override
    public char[][] readMap(Hero hero) {
         return readMap(hero, "map/wumpluszinput.txt");
    }

    public char[][] readMap(Hero hero, String path) {
        String line;
        int size;
        int col;
        int row;
        String position;

        int wumpusSize = 0;
        int goldSize = 0;

        char[][] map;

        InputStream inputStream = MapReaderImpl.class.getClassLoader().getResourceAsStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            //Első sor beolvasása
            line = bufferedReader.readLine();
            String[] firstLine = line.split("\\s+");
            size = Integer.valueOf(firstLine[0]);
            col = ((int) firstLine[1].charAt(0)) - 65;
            row = Integer.valueOf(firstLine[2]) - 1; // fájl első sorba 1 től indexeli
            position = firstLine[3];

            hero.setColPos(col);
            hero.setLookingDir(position);
            hero.setRowPos(row);

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

                    map[i][j] = fieldValue;

                    if ('U' == fieldValue) {
                        wumpusSize++;
                    } else if ('G' == fieldValue) {
                        goldSize++;
                    }

                    if (i == row && j == col) {
                        map[i][j] = 'H';
                    }
                }

                i++;
            }

        } catch (IOException e) {
            LOGGER.error("Map read failed!");
            throw new ReadMapException();
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

        return map;
    }

}
