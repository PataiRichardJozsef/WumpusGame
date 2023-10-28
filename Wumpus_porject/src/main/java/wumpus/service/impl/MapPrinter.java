package wumpus.service.impl;
import wumpus.model.Hero;
import wumpus.service.MapReader;

public class MapPrinter {

    private final Hero hero;
    private final MapReader reader = new MapReaderImpl();

    public MapPrinter(Hero hero) {
        this.hero = hero;
    }

    private char[][] map;

    public void loadMapFromFile() {
        map = reader.readMap(hero);
    }

    public void printMap() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                sb.append(map[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        sb.append(hero.toString()).append("\n");

        System.out.println(sb);
    }

    public char[][] getMap() {
        return map;
    }

    public Hero getHero() {
    	return this.hero;
    }
}
