package entities;

/**
 * Class representing a player in the game
 */
public class Player {
    private final String name;
    private Position currentPosition;

    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        this.name = name;
        this.currentPosition = new Position(1); // Start at position 1
    }

    public String getName() {
        return name;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void moveTo(Position newPosition) {
        if (newPosition == null) {
            throw new IllegalArgumentException("New position cannot be null");
        }
        this.currentPosition = newPosition;
    }

    public boolean hasWon() {
        return currentPosition.getValue() == 100;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', position=" + currentPosition.getValue() + '}';
    }
}
