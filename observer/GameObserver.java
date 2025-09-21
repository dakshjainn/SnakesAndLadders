package observer;

import entities.Player;
import entities.Position;

/**
 * Observer interface for game events
 */
public interface GameObserver {
    void onPlayerMoved(Player player, Position oldPosition, Position newPosition);
    void onSnakeEncountered(Player player, Position snakeHead, Position snakeTail);
    void onLadderClimbed(Player player, Position ladderBottom, Position ladderTop);
    void onPlayerWon(Player player);
    void onDiceRolled(Player player, int diceValue);
}
