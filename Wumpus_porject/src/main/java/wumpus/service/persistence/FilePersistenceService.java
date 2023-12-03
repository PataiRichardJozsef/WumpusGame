package wumpus.service.persistence;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import wumpus.util.GameStateMapper;
import wumpus.entity.Game;
import wumpus.model.state.GameState;

public class FilePersistenceService {
    public static final Path PERSISTENCE_STORE_PATH = Path.of("games.json");
    private final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public List<Game> findSavedGames(String userName) {
        List<Game> allGames = readAllGames();

        return allGames.stream().filter(game -> game.getUsername().equals(userName)).toList();
    }

    @SneakyThrows
    public Game findGameById(Integer id) {
        List<Game> allGames = readAllGames();

        return allGames.stream()
            .filter(game -> game.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException());
    }

    @SneakyThrows
    public void writeToFile(List<Game> games) {
        FileOutputStream outputStream = new FileOutputStream("games.json");
        var jsonString = mapper.writeValueAsString(games);
        outputStream.write(jsonString.getBytes());
    }

    @SneakyThrows
    private List<Game> readAllGames() {
        String content = Files.readString(PERSISTENCE_STORE_PATH, StandardCharsets.UTF_8);
        var allGames = mapper.readValue(content, new TypeReference<List<Game>>() {
        });
        return allGames;
    }

    @SneakyThrows
    public void saveGame(GameState gameState, String userName) {
        var allGames = readAllGames();
        var lastId = allGames.stream().max(new GameSortById()).get().getId() + 1;
        var newGame = GameStateMapper.buildGamaEntityFromState(gameState, userName);
        newGame.setId(lastId);
        allGames.add(newGame);
        writeToFile(allGames);
    }

    class GameSortById implements Comparator<Game> {
        public int compare(Game a, Game b) {
            return a.getId().compareTo(b.getId());
        }

    }

}
