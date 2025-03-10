package LLD.SnakeLadder;

import java.util.*;

class Player {
    private final String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

class Board {
    private final int SIZE = 100;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;

    public Board() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        initializeSnakesAndLadders();
    }

    private void initializeSnakesAndLadders() {
        // Snakes
        snakes.put(99, 10);
        snakes.put(65, 40);
        snakes.put(89, 53);
        snakes.put(46, 5);
        snakes.put(48, 9);

        // Ladders
        ladders.put(2, 38);
        ladders.put(7, 14);
        ladders.put(8, 31);
        ladders.put(15, 26);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(78, 98);
    }

    public int getSize() {
        return SIZE;
    }

    public int getNewPosition(int currentPosition) {
        if (snakes.containsKey(currentPosition)) {
            System.out.println("Oops! Snake bite! Going down!");
            return snakes.get(currentPosition);
        }
        if (ladders.containsKey(currentPosition)) {
            System.out.println("Yay! Climbing up the ladder!");
            return ladders.get(currentPosition);
        }
        return currentPosition;
    }

    public void displayBoard(Player player1, Player player2) {
        System.out.println("\nBoard Status:");
        for (int i = SIZE; i > 0; i -= 10) {
            for (int j = i; j > i - 10 && j > 0; j--) {
                if (j == player1.getPosition() && j == player2.getPosition()) {
                    System.out.print("[P1P2]");
                } else if (j == player1.getPosition()) {
                    System.out.print("[P1]");
                } else if (j == player2.getPosition()) {
                    System.out.print("[P2]");
                } else {
                    System.out.printf("[%3d]", j);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Dice {
    private final Random random;

    public Dice() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }
}

public class SnakeLadder {
    private final Board board;
    private final Dice dice;
    private final Player player1;
    private final Player player2;
    private final Scanner scanner;

    public SnakeLadder() {
        board = new Board();
        dice = new Dice();
        scanner = new Scanner(System.in);

        System.out.println("Enter name for Player 1:");
        player1 = new Player(scanner.nextLine());
        System.out.println("Enter name for Player 2:");
        player2 = new Player(scanner.nextLine());
    }

    public void play() {
        boolean gameEnded = false;
        Player currentPlayer = player1;

        while (!gameEnded) {
            board.displayBoard(player1, player2);
            System.out.println(currentPlayer.getName() + "'s turn. Press Enter to roll the dice...");
            scanner.nextLine();

            int diceValue = dice.roll();
            System.out.println("Rolled: " + diceValue);

            int newPosition = currentPlayer.getPosition() + diceValue;

            if (newPosition <= board.getSize()) {
                newPosition = board.getNewPosition(newPosition);
                currentPlayer.setPosition(newPosition);
                System.out.println(currentPlayer.getName() + " moved to position " + newPosition);

                if (newPosition == board.getSize()) {
                    System.out.println("\nCongratulations! " + currentPlayer.getName() + " wins!");
                    gameEnded = true;
                }
            } else {
                System.out.println("Need exact number to win. Try again in next turn.");
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        board.displayBoard(player1, player2);
        scanner.close();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Snake and Ladder Game!");
        SnakeLadder game = new SnakeLadder();
        game.play();
    }
}