package wumpus.model.hero;

public enum RotateDirection {
    LEFT,
    RIGHT;

    public static final String LEFT_TOKEN = "L";
    public static final String RIGHT_TOKEN = "R";

    public static final RotateDirection from(String dir) {
        switch (dir) {
        case LEFT_TOKEN:
            return LEFT;
        case RIGHT_TOKEN:
            return RIGHT;
        default:
            throw new IllegalArgumentException("Not acceptable direction:" + dir);
        }
    }
}
