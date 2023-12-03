package wumpus.util;

import java.util.Base64;

public final class GameStateSerializer {

    private GameStateSerializer() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String serialize(char[][] charArray) {
        StringBuilder sb = new StringBuilder();

        for (char[] row : charArray) {
            sb.append(row);
            sb.append('\n'); // Add newline between rows for better separation
        }

        return Base64.getEncoder().encodeToString(sb.toString().getBytes());
    }

    // Deserialize a string to a char[][]
    public static char[][] deserialize(String serializedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(serializedString.getBytes());
        String decodedString = new String(decodedBytes);
        String[] rows = decodedString.split("\n");
        int numRows = rows.length;
        int numCols = rows[0].length(); // Assuming all rows have the same length

        char[][] charArray = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            charArray[i] = rows[i].toCharArray();
        }

        return charArray;
    }
}
