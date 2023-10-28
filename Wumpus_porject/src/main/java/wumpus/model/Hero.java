package wumpus.model;

public class Hero {

    private String name;

    private String lookingDir;

    private int rowPos;

    private int colPos;

    private int arrowNum;

    public Hero(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLookingDir() {
        return lookingDir;
    }

    public void setLookingDir(String lookingDir) {
        this.lookingDir = lookingDir;
    }

    public int getRowPos() {
        return rowPos;
    }

    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public int getColPos() {
        return colPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    public int getArrowNum() {
        return arrowNum;
    }

    public void setArrowNum(int arrowNum) {
        this.arrowNum = arrowNum;
    }

    public String getHerolookingDir() {
        switch (this.lookingDir) {
        case "E":
            return "Kelet felé néz";
        case "W":
            return "Nyugat felé néz";
        case "N":
            return "Észak felé néz";
        case "S":
            return "Dél felé néz";
        default:
            return "Ismeretlen irány!";
        }
    }

    @Override
    public String toString() {
        return this.name + " nevű játékos " + getHerolookingDir();
    }

}
