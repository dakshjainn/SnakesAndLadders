package entities;

/**
 * Immutable class representing a snake on the board
 */
public final class Snake {
    private final Position head;
    private final Position tail;

    public Snake(Position head, Position tail) {
        if (head == null || tail == null) {
            throw new IllegalArgumentException("Head and tail positions cannot be null");
        }
        if (head.getValue() <= tail.getValue()) {
            throw new IllegalArgumentException("Snake head must be at a higher position than tail");
        }
        this.head = head;
        this.tail = tail;
    }

    public Position getHead() {
        return head;
    }

    public Position getTail() {
        return tail;
    }

    @Override
    public String toString() {
        return "Snake{head=" + head.getValue() + ", tail=" + tail.getValue() + '}';
    }
}
