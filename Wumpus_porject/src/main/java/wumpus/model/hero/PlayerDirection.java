package wumpus.model.hero;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlayerDirection {

    NORTH(Direction.WEST, Direction.EAST),
    WEST(Direction.SOUTH, Direction.NORTH),
    SOUTH(Direction.EAST, Direction.WEST),
    EAST(Direction.NORTH, Direction.SOUTH);

    public final Direction directionToLeft;
    public final Direction directionToRight;

    public static final PlayerDirection from(String dir) {
        switch (dir) {
        case "N":
            return NORTH;
        case "W":
            return WEST;
        case "E":
            return EAST;
        case "S":
            return SOUTH;
        default:
            throw new IllegalArgumentException("Not acceptable direction:" + dir);
        }
    }
}
