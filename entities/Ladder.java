package entities;

/**
 * Immutable class representing a ladder on the board
 */
public final class Ladder {
    private final Position bottom;
    private final Position top;

    public Ladder(Position bottom, Position top) {
        if (bottom == null || top == null) {
            throw new IllegalArgumentException("Bottom and top positions cannot be null");
        }
        if (bottom.getValue() >= top.getValue()) {
            throw new IllegalArgumentException("Ladder bottom must be at a lower position than top");
        }
        this.bottom = bottom;
        this.top = top;
    }

    public Position getBottom() {
        return bottom;
    }

    public Position getTop() {
        return top;
    }

    @Override
    public String toString() {
        return "Ladder{bottom=" + bottom.getValue() + ", top=" + top.getValue() + '}';
    }
}
