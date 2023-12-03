package wumpus.model.hero;

public enum Direction {
    WEST,
    SOUTH,
    NORTH,
    EAST;


    public PlayerDirection toPlayerDirection(Direction dir) {
        switch (dir) {
        case WEST:
            return PlayerDirection.WEST;
        case EAST:
            return PlayerDirection.EAST;
        case NORTH:
            return PlayerDirection.NORTH;
        case SOUTH:
            return PlayerDirection.SOUTH;
        default:
            throw new IllegalStateException();
        }
    }

}
