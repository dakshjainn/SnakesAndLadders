import board.Board;
import dice.Dice;
import dice.StandardDice;
import entities.Player;
import factory.BoardFactory;
import game.Game;
import observer.ConsoleGameObserver;

import java.util.Scanner;

/**
 * Main application class for Snakes and Ladders game
 */
public class SnakesAndLaddersApplication {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Snakes and Ladders!");
        System.out.println("===============================\n");
        
        // Create board using factory
        Board board = BoardFactory.createStandardBoard();
        
        // Create dice with standard strategy
        Dice dice = new Dice(new StandardDice());
        
        // Get game instance (Singleton)
        Game game = Game.getInstance(board, dice);
        
        // Add console observer
        game.addObserver(new ConsoleGameObserver());
        
        // Get number of players
        System.out.print("Enter number of players (2-4): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (numPlayers < 2 || numPlayers > 4) {
            System.out.println("Invalid number of players. Must be between 2 and 4.");
            return;
        }
        
        // Add players
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            game.addPlayer(new Player(playerName));
        }
        
        System.out.println("\nGame started! Press Enter to roll dice for each turn.\n");
        
        // Game loop
        while (!game.isGameOver()) {
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println("\n" + currentPlayer.getName() + "'s turn (Position: " + 
                             currentPlayer.getCurrentPosition().getValue() + ")");
            System.out.print("Press Enter to roll dice...");
            scanner.nextLine();
            
            game.playTurn();
        }
        
        System.out.println("\nGame Over!");
        
        // Display final positions
        System.out.println("\nFinal Positions:");
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName() + ": " + player.getCurrentPosition().getValue());
        }
        
        scanner.close();
    }
}
