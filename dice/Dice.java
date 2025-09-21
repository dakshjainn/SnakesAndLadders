package dice;

/**
 * Dice class using Strategy pattern
 */
public class Dice {
    private DiceStrategy strategy;

    public Dice(DiceStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Dice strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public int roll() {
        return strategy.roll();
    }

    public void setStrategy(DiceStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Dice strategy cannot be null");
        }
        this.strategy = strategy;
    }
}
