package dice;

import java.util.List;
import java.util.ArrayList;

/**
 * Loaded dice implementation for testing - returns predetermined values
 */
public class LoadedDice implements DiceStrategy {
    private final List<Integer> values;
    private int currentIndex;

    public LoadedDice(List<Integer> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Values list cannot be null or empty");
        }
        this.values = new ArrayList<>(values);
        this.currentIndex = 0;
    }

    @Override
    public int roll() {
        int value = values.get(currentIndex);
        currentIndex = (currentIndex + 1) % values.size();
        return value;
    }
}
