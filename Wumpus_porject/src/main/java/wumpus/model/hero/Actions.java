package wumpus.model.hero;

public enum Actions {
    SHOOT,
    ROTATE,
    STEP,
    COLLECT;

    public static final Actions from(String actStr) {
        switch (actStr) {
        case "1":
            return STEP;
        case "2":
            return ROTATE;
        case "3":
            return COLLECT;
        case "4":
            return SHOOT;
        default:
            throw new IllegalArgumentException("Not acceptable action:" + actStr);
        }
    }
}
