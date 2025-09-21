package factory;

import board.Board;
import entities.Snake;
import entities.Ladder;
import entities.Position;

/**
 * Factory class for creating game boards
 */
public class BoardFactory {

    /**
     * Creates a standard board with default snakes and ladders configuration
     */
    public static Board createStandardBoard() {
        return new Board.Builder()
                // Add snakes
                .addSnake(new Snake(new Position(99), new Position(54)))
                .addSnake(new Snake(new Position(95), new Position(75)))
                .addSnake(new Snake(new Position(93), new Position(73)))
                .addSnake(new Snake(new Position(87), new Position(24)))
                .addSnake(new Snake(new Position(62), new Position(19)))
                .addSnake(new Snake(new Position(64), new Position(60)))
                .addSnake(new Snake(new Position(54), new Position(34)))
                .addSnake(new Snake(new Position(17), new Position(7)))
                
                // Add ladders
                .addLadder(new Ladder(new Position(4), new Position(14)))
                .addLadder(new Ladder(new Position(9), new Position(31)))
                .addLadder(new Ladder(new Position(20), new Position(38)))
                .addLadder(new Ladder(new Position(28), new Position(84)))
                .addLadder(new Ladder(new Position(40), new Position(59)))
                .addLadder(new Ladder(new Position(51), new Position(67)))
                .addLadder(new Ladder(new Position(63), new Position(81)))
                .addLadder(new Ladder(new Position(71), new Position(91)))
                
                .build();
    }

    /**
     * Creates a simple board with fewer snakes and ladders for testing
     */
    public static Board createSimpleBoard() {
        return new Board.Builder()
                // Add snakes
                .addSnake(new Snake(new Position(16), new Position(6)))
                .addSnake(new Snake(new Position(47), new Position(26)))
                .addSnake(new Snake(new Position(49), new Position(11)))
                .addSnake(new Snake(new Position(56), new Position(53)))
                
                // Add ladders
                .addLadder(new Ladder(new Position(1), new Position(38)))
                .addLadder(new Ladder(new Position(4), new Position(14)))
                .addLadder(new Ladder(new Position(8), new Position(30)))
                .addLadder(new Ladder(new Position(21), new Position(42)))
                
                .build();
    }

    /**
     * Creates an empty board with no snakes or ladders
     */
    public static Board createEmptyBoard() {
        return new Board.Builder().build();
    }
}
