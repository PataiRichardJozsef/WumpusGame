package wumpus.model.hero;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
public class Hero {

    private final String name;

    private Integer numberOfArrows;

    private PlayerDirection direction;

    private Boolean hasGold = false;

    public void shoot() {
        this.numberOfArrows = this.numberOfArrows - 1;
    }

    public Hero(String name, Integer numberOfArrows, PlayerDirection direction, Boolean hasGold) {
        this.name = name;
        this.numberOfArrows = numberOfArrows;
        this.direction = direction;
        this.hasGold = hasGold;
    }

    public PlayerDirection rotate(RotateDirection rotation) {
        switch (rotation) {
        case LEFT:
            return this.direction.getDirectionToLeft().toPlayerDirection(this.direction.getDirectionToLeft());
        case RIGHT:
            return this.direction.getDirectionToRight().toPlayerDirection(this.direction.getDirectionToRight());
        default: return null;
        }
    }
}
