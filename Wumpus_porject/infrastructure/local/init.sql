CREATE TABLE players (
    username TEXT PRIMARY KEY,
    number_of_wins INTEGER NOT NULL
);

CREATE TABLE games (
  id SERIAL PRIMARY KEY,
  username TEXT,
  game_state TEXT,
  row_position INTEGER,
  column_position INTEGER,
  number_of_arrows INTEGER,
  direction TEXT,
  start_row_position INTEGER,
  start_column_position INTEGER,
  has_gold BOOL,
  number_of_steps INTEGER
);

CREATE INDEX idx_username_on_games ON games USING HASH (username);

-- Test data
INSERT INTO players VALUES ('Jozsi', 10);
INSERT INTO players VALUES ('Pisti', 22);
INSERT INTO players VALUES ('Geza', 1);

INSERT INTO games (username, game_state, row_position, column_position, number_of_arrows, direction, start_row_position, start_column_position, has_gold, number_of_steps) VALUES ('kutyus', 'V1dXV1dXCldfX19QVwpXVV9QX1cKV19fX19XCldfX1BfVwpXV1dXV1cK', 3, 1, 1, 'WEST', 1, 4, true, 3);
INSERT INTO games (username, game_state, row_position, column_position, number_of_arrows, direction, start_row_position, start_column_position, has_gold, number_of_steps) VALUES ('kutyus', 'V1dXV1dXCldfX19QVwpXVV9QX1cKV19HX19XCldfX1BfVwpXV1dXV1cK', 4, 2, 1, 'EAST', 1, 4, false, 1);
