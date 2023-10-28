package wumpus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wumpus.service.impl.Menu;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Jatek elkezdődött!");

        Menu menu = new Menu();
        menu.start();

    }

}
