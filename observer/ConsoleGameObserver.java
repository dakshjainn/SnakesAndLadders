package observer;

import entities.Player;
import entities.Position;

/**
 * Console implementation of GameObserver for printing game events
 */
public class ConsoleGameObserver implements GameObserver {
    
    @Override
    public void onPlayerMoved(Player player, Position oldPosition, Position newPosition) {
        System.out.println(player.getName() + " moved from " + oldPosition.getValue() + 
                         " to " + newPosition.getValue());
    }

    @Override
    public void onSnakeEncountered(Player player, Position snakeHead, Position snakeTail) {
        System.out.println("Oh no! " + player.getName() + " encountered a snake at " + 
                         snakeHead.getValue() + " and slid down to " + snakeTail.getValue());
    }

    @Override
    public void onLadderClimbed(Player player, Position ladderBottom, Position ladderTop) {
        System.out.println("Great! " + player.getName() + " climbed a ladder from " + 
                         ladderBottom.getValue() + " to " + ladderTop.getValue());
    }

    @Override
    public void onPlayerWon(Player player) {
        System.out.println("\n🎉 Congratulations! " + player.getName() + " has won the game! 🎉");
    }

    @Override
    public void onDiceRolled(Player player, int diceValue) {
        System.out.println("\n" + player.getName() + " rolled a " + diceValue);
    }
}
