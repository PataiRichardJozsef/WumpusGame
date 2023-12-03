package wumpus.service.persistence;

import java.util.List;

import wumpus.util.GameStateMapper;
import wumpus.dao.GameDAO;
import wumpus.dao.PlayerDAO;
import wumpus.entity.Game;
import wumpus.entity.Player;
import wumpus.model.state.GameState;

public class PersistenceService {
    public static final int DEFAULT_NUMBER_OF_WINS = 0;
    private final GameDAO gameDAO = new GameDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    public void saveGame(GameState gameState, String userName) {
        var existingPlayer = playerDAO.findByUsername(userName);
        if (existingPlayer == null) {
            playerDAO.save(new Player(userName, 0));
        }

        var gameEntity = GameStateMapper.buildGamaEntityFromState(gameState, userName);
        gameDAO.save(gameEntity);
    }

    public String initUser(String userName) {
        var existingPlayer = playerDAO.findByUsername(userName);
        if (existingPlayer == null) {
            playerDAO.save(new Player(userName, DEFAULT_NUMBER_OF_WINS));
        }
        return userName;
    }

    public List<Game> findSavedGames(String userName) {
        return gameDAO.findByUsername(userName);
    }

    public Game findGameById(String userInput) {
        return gameDAO.findById(Integer.valueOf(userInput));
    }

    public List<Player> getLeaderboard() {
        return playerDAO.findTop(10);
    }

}
