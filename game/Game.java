package game;

import board.Board;
import dice.Dice;
import entities.Player;
import entities.Position;
import entities.Snake;
import entities.Ladder;
import observer.GameObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Main game class implementing Singleton pattern and Observer pattern
 */
public class Game {
    private static Game instance;
    
    private final Board board;
    private final Dice dice;
    private final List<Player> players;
    private final List<GameObserver> observers;
    private int currentPlayerIndex;
    private boolean isGameOver;

    private Game(Board board, Dice dice) {
        this.board = board;
        this.dice = dice;
        this.players = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.isGameOver = false;
    }

    /**
     * Get singleton instance
     */
    public static Game getInstance(Board board, Dice dice) {
        if (instance == null) {
            instance = new Game(board, dice);
        }
        return instance;
    }

    /**
     * Reset the game instance
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Add a player to the game
     */
    public void addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (players.size() >= 4) {
            throw new IllegalStateException("Maximum 4 players allowed");
        }
        players.add(player);
    }

    /**
     * Add an observer
     */
    public void addObserver(GameObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    /**
     * Remove an observer
     */
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    /**
     * Play one turn
     */
    public void playTurn() {
        if (isGameOver) {
            throw new IllegalStateException("Game is already over");
        }
        if (players.isEmpty()) {
            throw new IllegalStateException("No players in the game");
        }

        Player currentPlayer = players.get(currentPlayerIndex);
        int diceValue = dice.roll();
        
        // Notify observers about dice roll
        notifyDiceRolled(currentPlayer, diceValue);

        // Calculate new position
        int currentPos = currentPlayer.getCurrentPosition().getValue();
        int newPos = currentPos + diceValue;

        // Check if new position exceeds board size
        if (newPos > board.getSize()) {
            // Player cannot move beyond the board
            System.out.println(currentPlayer.getName() + " needs exactly " + 
                             (board.getSize() - currentPos) + " to win");
            nextPlayer();
            return;
        }

        // Move player
        Position oldPosition = currentPlayer.getCurrentPosition();
        Position newPosition = new Position(newPos);
        currentPlayer.moveTo(newPosition);
        notifyPlayerMoved(currentPlayer, oldPosition, newPosition);

        // Check for snake
        Optional<Snake> snake = board.getSnakeAt(newPosition);
        if (snake.isPresent()) {
            currentPlayer.moveTo(snake.get().getTail());
            notifySnakeEncountered(currentPlayer, snake.get().getHead(), snake.get().getTail());
        }

        // Check for ladder
        Optional<Ladder> ladder = board.getLadderAt(newPosition);
        if (ladder.isPresent()) {
            currentPlayer.moveTo(ladder.get().getTop());
            notifyLadderClimbed(currentPlayer, ladder.get().getBottom(), ladder.get().getTop());
        }

        // Check if player won
        if (currentPlayer.hasWon()) {
            isGameOver = true;
            notifyPlayerWon(currentPlayer);
        } else {
            nextPlayer();
        }
    }

    /**
     * Get current player
     */
    public Player getCurrentPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        return players.get(currentPlayerIndex);
    }

    /**
     * Check if game is over
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Get list of players
     */
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    // Observer notification methods
    private void notifyPlayerMoved(Player player, Position oldPosition, Position newPosition) {
        for (GameObserver observer : observers) {
            observer.onPlayerMoved(player, oldPosition, newPosition);
        }
    }

    private void notifySnakeEncountered(Player player, Position snakeHead, Position snakeTail) {
        for (GameObserver observer : observers) {
            observer.onSnakeEncountered(player, snakeHead, snakeTail);
        }
    }

    private void notifyLadderClimbed(Player player, Position ladderBottom, Position ladderTop) {
        for (GameObserver observer : observers) {
            observer.onLadderClimbed(player, ladderBottom, ladderTop);
        }
    }

    private void notifyPlayerWon(Player player) {
        for (GameObserver observer : observers) {
            observer.onPlayerWon(player);
        }
    }

    private void notifyDiceRolled(Player player, int diceValue) {
        for (GameObserver observer : observers) {
            observer.onDiceRolled(player, diceValue);
        }
    }
}
