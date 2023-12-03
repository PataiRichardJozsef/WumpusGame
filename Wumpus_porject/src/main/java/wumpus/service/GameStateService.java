package wumpus.service;

import static wumpus.model.state.GameState.EMPTY_PLACE;
import static wumpus.model.state.GameState.GOLD;
import static wumpus.model.state.GameState.INVALID;
import static wumpus.model.state.GameState.PROGRESS;
import static wumpus.model.state.GameState.STACK;
import static wumpus.model.state.GameState.WINNER;
import static wumpus.model.state.GameState.WUMPUSZ;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import wumpus.model.hero.Actions;
import wumpus.model.hero.Hero;
import wumpus.model.hero.RotateDirection;
import wumpus.model.state.GameState;

@RequiredArgsConstructor
public class GameStateService {

    private final MapReader mapReader;

    public GameState init(GameState state) {
        return mapReader.readMap(state);
    }

    public char execute(GameState state, Actions action, String additionalAttr) {
        switch (action) {
        case ROTATE:
            setPlayerDirection(state, additionalAttr);
            break;
        case SHOOT:
            return shoot(state);
        case STEP:
            return step(state);
        case COLLECT:
            collect(state);
            break;
        default:
            throw new RuntimeException("TODO");
        }

        return ' ';
    }

    private char shoot(GameState state) {
        if (Integer.valueOf(0).equals(state.getHero().getNumberOfArrows())) {
            return INVALID;
        }

        state.getHero().shoot();
        applyShootOnMap(state);
        return PROGRESS;
    }

    private void collect(GameState state) {
        if (state.getMap()[state.getRowPos()][state.getColumnPos()] == GOLD) {
            state.getHero().setHasGold(true);
            state.getMap()[state.getRowPos()][state.getColumnPos()] = EMPTY_PLACE;
        }
    }

    private char step(GameState state) {
        var newPosition = getNewPosition(state);
        var typeOfNewPosition = validateNewPosition(state, newPosition);
        if (typeOfNewPosition != INVALID) {
            setNewPosition(state, newPosition, typeOfNewPosition);
        }

        if (isWinner(state)) {
            return WINNER;
        }

        var numberOfSteps = state.getNumberOfSteps();
        ++numberOfSteps;
        state.setNumberOfSteps(numberOfSteps);

        return typeOfNewPosition;
    }

    private boolean isWinner(GameState state) {
        return state.getHero().getHasGold() == true && state.getColumnPos() == state.getStartPosition().getRight()
            && state.getRowPos() == state.getStartPosition().getLeft();
    }

    private void setNewPosition(GameState state, Pair<Integer, Integer> newPosition, char typeOfNewPosition) {
        if (typeOfNewPosition == STACK) {
            state.getHero().setNumberOfArrows(state.getHero().getNumberOfArrows() - 1);
        }

        state.setColumnPos(newPosition.getRight());
        state.setRowPos(newPosition.getLeft());
    }

    private char validateNewPosition(GameState state, Pair<Integer, Integer> newPosition) {
        var validPlaces = List.of(EMPTY_PLACE, WUMPUSZ, STACK, GOLD);
        if (validPlaces.contains(state.getMap()[newPosition.getLeft()][newPosition.getRight()])) {
            return state.getMap()[newPosition.getLeft()][newPosition.getRight()];
        }

        return INVALID;
    }

    private Pair<Integer, Integer> getNewPosition(GameState state) {
        return getNewPosition(state.getHero(), state.getRowPos(), state.getColumnPos());
    }

    private Pair<Integer, Integer> getNewPosition(Hero hero, int rowPos, int columnPos) {
        switch (hero.getDirection()) {
        case NORTH:
            return Pair.of(rowPos - 1, columnPos);
        case WEST:
            return Pair.of(rowPos, columnPos - 1);
        case EAST:
            return Pair.of(rowPos, columnPos + 1);
        case SOUTH:
            return Pair.of(rowPos + 1, columnPos);
        default:
            throw new IllegalArgumentException("Invalid direction");
        }
    }

    private void applyShootOnMap(GameState state) {
        Integer flyingArrowRow = Integer.valueOf(state.getRowPos());
        Integer flyingArrowCol = Integer.valueOf(state.getColumnPos());

        var newArrowPosition = getNewPosition(state.getHero(), flyingArrowRow, flyingArrowCol);
        char objectUnderArrow = state.getMap()[newArrowPosition.getLeft()][newArrowPosition.getRight()];
        while (List.of(EMPTY_PLACE, GOLD, STACK).contains(objectUnderArrow)) {
            newArrowPosition = getNewPosition(state.getHero(), flyingArrowRow, flyingArrowCol);
            flyingArrowRow = newArrowPosition.getLeft();
            flyingArrowCol = newArrowPosition.getRight();
            objectUnderArrow = state.getMap()[newArrowPosition.getLeft()][newArrowPosition.getRight()];
        }

        evaluateHit(state, objectUnderArrow, newArrowPosition);
    }

    private void evaluateHit(GameState state, char objectUnderArrow, Pair<Integer, Integer> arrowPosition) {
        if (WUMPUSZ == objectUnderArrow) {
            removeWumpusz(state, arrowPosition);
        }
    }

    private void removeWumpusz(GameState state, Pair<Integer, Integer> arrowPosition) {
        state.getMap()[arrowPosition.getLeft()][arrowPosition.getRight()] = EMPTY_PLACE;
    }

    private void setPlayerDirection(GameState state, String directionStr) {
        RotateDirection direction = RotateDirection.from(directionStr);
        var newDir = state.getHero().rotate(direction);
        state.getHero().setDirection(newDir);
    }

}
