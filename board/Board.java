package board;

import entities.Snake;
import entities.Ladder;
import entities.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Board class representing the game board
 */
public class Board {
    private final Map<Position, Snake> snakes;
    private final Map<Position, Ladder> ladders;
    private final int size;

    private Board(Builder builder) {
        this.snakes = new HashMap<>(builder.snakes);
        this.ladders = new HashMap<>(builder.ladders);
        this.size = builder.size;
    }

    public Optional<Snake> getSnakeAt(Position position) {
        return Optional.ofNullable(snakes.get(position));
    }

    public Optional<Ladder> getLadderAt(Position position) {
        return Optional.ofNullable(ladders.get(position));
    }

    public int getSize() {
        return size;
    }

    /**
     * Builder class for Board
     */
    public static class Builder {
        private final Map<Position, Snake> snakes = new HashMap<>();
        private final Map<Position, Ladder> ladders = new HashMap<>();
        private int size = 100; // Default board size

        public Builder withSize(int size) {
            if (size < 10 || size > 100) {
                throw new IllegalArgumentException("Board size must be between 10 and 100");
            }
            this.size = size;
            return this;
        }

        public Builder addSnake(Snake snake) {
            if (snake == null) {
                throw new IllegalArgumentException("Snake cannot be null");
            }
            
            // Check if position is already occupied
            if (snakes.containsKey(snake.getHead()) || ladders.containsKey(snake.getHead())) {
                throw new IllegalStateException("Position " + snake.getHead().getValue() + 
                                              " is already occupied");
            }
            
            snakes.put(snake.getHead(), snake);
            return this;
        }

        public Builder addLadder(Ladder ladder) {
            if (ladder == null) {
                throw new IllegalArgumentException("Ladder cannot be null");
            }
            
            // Check if position is already occupied
            if (snakes.containsKey(ladder.getBottom()) || ladders.containsKey(ladder.getBottom())) {
                throw new IllegalStateException("Position " + ladder.getBottom().getValue() + 
                                              " is already occupied");
            }
            
            ladders.put(ladder.getBottom(), ladder);
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
