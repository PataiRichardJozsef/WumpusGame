package wumpus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "games")
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @Column(name = "game_state")
    private String gameState;
    @Column(name = "row_position")
    private Integer rowPosition;
    @Column(name = "column_position")
    private Integer columnPosition;
    @Column(name = "number_of_arrows")
    private Integer numberOfArrows;
    private String direction;

    @Column(name = "start_row_position")
    private Integer startRowPosition;
    @Column(name = "start_column_position")
    private Integer startColumnPosition;
    @Column(name = "has_gold")
    private Boolean hasGold;
    @Column(name = "number_of_steps")
    private Integer numberOfSteps;
}
