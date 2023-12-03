package wumpus.model.state;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import wumpus.model.hero.Hero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameState {
    public static final char INVALID = 'I';
    public static final char WINNER = 'W';
    public static final char EMPTY_PLACE = '_';
    public static final char WUMPUSZ = 'U';
    public static final char STACK = 'P';
    public static final char GOLD = 'G';
    public static final char HERO = 'H';
    public static final char START_POSITION = '*';
    public static final char PROGRESS = '>';
    private Hero hero;
    private char[][] map;
    private Integer columnPos;
    private Integer rowPos;
    private Integer numberOfSteps = 0;
    private Pair<Integer, Integer> startPosition;

    public void print() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                var elementToPrint = map[i][j];
                if (i == startPosition.getLeft() && j == startPosition.getRight()) {
                    elementToPrint = START_POSITION;
                }

                if (i == rowPos && j == columnPos) {
                    elementToPrint = HERO;
                }
                System.out.print(elementToPrint + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(hero);
        System.out.println();
    }
}
