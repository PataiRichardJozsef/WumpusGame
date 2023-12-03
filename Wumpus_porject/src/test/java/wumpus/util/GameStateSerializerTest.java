package wumpus.util;


import static org.assertj.core.api.Assertions.assertThat;
import static wumpus.TestConstants.GAME_STATE;

import org.junit.jupiter.api.Test;

class GameStateSerializerTest {

    @Test
    void serialize_and_deserialize() {
        var deserializedGameState = GameStateSerializer.deserialize(GAME_STATE);

        assertThat(GameStateSerializer.serialize(deserializedGameState)).isEqualTo(GAME_STATE);
    }
}
