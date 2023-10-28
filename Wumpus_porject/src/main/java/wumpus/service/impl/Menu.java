package wumpus.service.impl;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.model.Hero;

public class Menu {
    private static final Logger LOGGER = LoggerFactory.getLogger(Menu.class);

    public MapPrinter start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kérem adja meg a felhasználónevét: ");
        String felhasznalonev = scanner.nextLine();

        Hero jatekos = new Hero(felhasznalonev);

        MapPrinter map = new MapPrinter(jatekos);

        select(scanner, felhasznalonev, map);

        return map;
    }

    private void select(Scanner scanner, String username, MapPrinter map) {
        printHeader(username);

        int selectedNum = scanner.nextInt();
        switch (selectedNum) {
            case 1:
                loadMap(map, "Pálya betöltve:\n");
                break;
            case 2:
                loadMap(map, "Játék inditása:\n");
                play(map);
            case 3:
                System.exit(1);
            default:
                defaultResponse(scanner, username, map);
        }
    }

    private void printHeader(String felhasznalonev) {
        System.out.println("Sikeres bejelentkezés " + felhasznalonev + " felhasználóval! Kérem válasszon:");
        System.out.println("[1] Pálya betöltése fájlból");
        System.out.println("[2] Játék indítása");
        System.out.println("[3] Kilépés");
    }

    private void play(MapPrinter map) {
        Scanner mozgasScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Kérem adja meg az irányt (E/W/N/S, üres sztring a kilépéshez): ");
            String mozgas = mozgasScanner.nextLine();
            if (mozgas.equalsIgnoreCase("FELAD")) {
                break; // Ha az input üres sztring, akkor kilépünk a ciklusból.
            }
            LOGGER.info("A hös forgatasa mozgatasa majd bekerül a hos osztalyba!\n");
            map.getHero().setLookingDir(mozgas);
            map.printMap();
        }

        mozgasScanner.close();
    }

    private void defaultResponse(Scanner scanner, String felhasznalonev, MapPrinter map) {
        System.out.println("Kérem a menüből válasszon!");
        select(scanner, felhasznalonev, map);
    }

    private void loadMap(MapPrinter map, String x) {
        map.loadMapFromFile();
        System.out.println(x);
        map.printMap();
    }

}
