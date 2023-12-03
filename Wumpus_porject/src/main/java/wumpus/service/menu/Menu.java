package wumpus.service.menu;

import static wumpus.model.hero.RotateDirection.LEFT_TOKEN;
import static wumpus.model.hero.RotateDirection.RIGHT_TOKEN;
import static wumpus.model.state.GameState.INVALID;
import static wumpus.model.state.GameState.WINNER;
import static wumpus.model.state.GameState.WUMPUSZ;
import static wumpus.service.menu.MenuPrinter.printDefault;
import static wumpus.service.menu.MenuPrinter.printDirectionRequest;
import static wumpus.service.menu.MenuPrinter.printGameOver;
import static wumpus.service.menu.MenuPrinter.printGiveUp;
import static wumpus.service.menu.MenuPrinter.printHeader;
import static wumpus.service.menu.MenuPrinter.printInvalid;
import static wumpus.service.menu.MenuPrinter.printLeaderboardHeader;
import static wumpus.service.menu.MenuPrinter.printLoadGame;
import static wumpus.service.menu.MenuPrinter.printOptions;
import static wumpus.service.menu.MenuPrinter.printOutOfArrow;
import static wumpus.service.menu.MenuPrinter.printSavedGames;
import static wumpus.service.menu.MenuPrinter.printUsernameRequest;
import static wumpus.service.menu.MenuPrinter.printVictory;

import java.util.List;
import java.util.Scanner;

import lombok.SneakyThrows;
import wumpus.service.MapReader;
import wumpus.util.GameStateMapper;
import wumpus.entity.Game;
import wumpus.entity.Player;
import wumpus.model.hero.Actions;
import wumpus.model.hero.Hero;
import wumpus.model.state.GameState;
import wumpus.service.GameStateService;
import wumpus.service.persistence.FilePersistenceService;
import wumpus.service.persistence.PersistenceService;

public class Menu {
    public static final String SAVE_TOKEN = "5";
    private final GameStateService gameStateService = new GameStateService(new MapReader());
    private final PersistenceService persistenceService = new PersistenceService();
    private final FilePersistenceService filePersistenceService = new FilePersistenceService();
    private GameState gameState;

    public void init(String name) {
        var hero = new Hero(name);
        gameState = new GameState();
        gameState.setHero(hero);
        gameStateService.init(gameState);
        gameState.print();

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        String userName = initUser(scanner);
        printHeader(userName);

        int selectedNum = scanner.nextInt();
        switch (selectedNum) {
        case 1:
            init(userName);
            playExisting(userName);
            break;
        case 2:
            init(userName);
            playExistingFromFile(userName);
            break;
        case 3:
            init(userName);
            play(userName);
        case 4:
            System.exit(1);
        case 5:
            printLeaderboard();
        default:
            printDefault(userName);
        }
    }

    private void printLeaderboard() {
        var leaderboard = persistenceService.getLeaderboard();
        printLeaderboard(leaderboard);
    }

    private void printLeaderboard(List<Player> leaderboard) {
        printLeaderboardHeader();
        int rank = 1;
        for (Player player : leaderboard) {
            System.out.println("" + rank + ": " + player.getUsername() + ", " + player.getNumberOfWins());
        }
    }

    private void playExisting(String userName) {
        Integer userInput = loadFromDatasource(userName, persistenceService.findSavedGames(userName));

        var existingGame = persistenceService.findGameById(userInput.toString());
        gameState = GameStateMapper.buildGamaStateFromEntity(existingGame);
        gameState.print();
        play(userName);
    }

    @SneakyThrows
    private void playExistingFromFile(String userName) {
        Integer userInput = loadFromDatasource(userName, filePersistenceService.findSavedGames(userName));

        var existingGame = filePersistenceService.findGameById(Integer.valueOf(userInput));
        gameState = GameStateMapper.buildGamaStateFromEntity(existingGame);
        gameState.print();
        play(userName);
    }

    private Integer loadFromDatasource(String userName, List<Game> games) {
        Scanner scanner = new Scanner(System.in);
        printSavedGames();
        games.forEach(game -> {
            var state = GameStateMapper.buildGamaStateFromEntity(game);
            System.out.println(game.getId().toString() + ": ");
            state.print();
        });
        printLoadGame();
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("X")) {
            play(userName);
        }

        return Integer.valueOf(userInput);
    }

    private String initUser(Scanner scanner) {
        printUsernameRequest();
        String userName = scanner.nextLine();
        return persistenceService.initUser(userName);
    }

    private void play(String userName) {
        Scanner actionScanner = new Scanner(System.in);
        while (true) {
            printOptions();
            String actionStr = actionScanner.nextLine();
            Actions action;
            String rotateDirection = null;

            if (actionStr.equals(SAVE_TOKEN)) {
                persistenceService.saveGame(gameState,userName);
                filePersistenceService.saveGame(gameState, userName);
            }

            try {
                action = Actions.from(actionStr);
            } catch (Exception e) {
                printGiveUp();
                break;
            }

            if (action == Actions.ROTATE) {
                printDirectionRequest();
                rotateDirection = actionScanner.nextLine();
                while (!isValidRotateDirection(rotateDirection)) {
                    printDirectionRequest();
                    rotateDirection = actionScanner.nextLine();
                }
            }

            var outcome = gameStateService.execute(gameState, action, rotateDirection);

            if (action == Actions.SHOOT && outcome == INVALID) {
                printOutOfArrow();
            }

            if (action == Actions.STEP) {
                if (outcome == INVALID) {
                    printInvalid();
                }

                if (outcome == WUMPUSZ) {
                    printGameOver();
                    break;
                }

                if (outcome == WINNER) {
                    printVictory(gameState.getNumberOfSteps());
                    break;
                }
            }
            gameState.print();
        }
        actionScanner.close();
    }

    private boolean isValidRotateDirection(String rotateDirection) {
        return List.of(LEFT_TOKEN, RIGHT_TOKEN).contains(rotateDirection);
    }

}
