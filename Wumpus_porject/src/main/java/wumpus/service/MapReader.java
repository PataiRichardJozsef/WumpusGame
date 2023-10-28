package wumpus.service;

import wumpus.model.Hero;

public interface MapReader {

	char[][] readMap(Hero herolookingDir);
	
}
