package dice;

import java.util.Random;

/**
 * Standard 6-sided dice implementation
 */
public class StandardDice implements DiceStrategy {
    private final Random random;

    public StandardDice() {
        this.random = new Random();
    }

    @Override
    public int roll() {
        return random.nextInt(6) + 1; // Returns 1-6
    }
}
