package Misc;

import java.util.Scanner;

enum PieceType {
    X,
    O
}

class PlayingPiece {
    PieceType pieceType;

    PlayingPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}

class PlayingPieceX extends PlayingPiece {
    public PlayingPieceX() {
        super(PieceType.X);
    }
}

class PlayingPieceO extends PlayingPiece {
    public PlayingPieceO() {
        super(PieceType.O);
    }
}

class Board {
    private PlayingPiece[][] board;
    private int size;

    public Board(int size) {
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == null;
    }

    public boolean placePiece(PlayingPiece piece, int row, int col) {
        if (isCellEmpty(row, col)) {
            board[row][col] = piece;
            return true;
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" " + board[i][j].getPieceType() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean checkWinner(PlayingPiece piece) {
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (checkRow(i, piece) || checkColumn(i, piece)) {
                return true;
            }
        }
        // Check diagonals
        return checkDiagonal(piece);
    }

    private boolean checkRow(int row, PlayingPiece piece) {
        for (int i = 0; i < size; i++) {
            if (board[row][i] == null || board[row][i].getPieceType() != piece.getPieceType()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col, PlayingPiece piece) {
        for (int i = 0; i < size; i++) {
            if (board[i][col] == null || board[i][col].getPieceType() != piece.getPieceType()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(PlayingPiece piece) {
        boolean diagonal1 = true;
        boolean diagonal2 = true;

        for (int i = 0; i < size; i++) {
            if (board[i][i] == null || board[i][i].getPieceType() != piece.getPieceType()) {
                diagonal1 = false;
            }
            if (board[i][size - 1 - i] == null || board[i][size - 1 - i].getPieceType() != piece.getPieceType()) {
                diagonal2 = false;
            }
        }
        return diagonal1 || diagonal2;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getSize() {
        return size;
    }
}

class Player {
    private String name;
    private PlayingPiece piece;

    public Player(String name, PlayingPiece piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return name;
    }

    public PlayingPiece getPiece() {
        return piece;
    }
}

public class TicTacToe {
    private static Scanner scanner = new Scanner(System.in);
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public TicTacToe(int size) {
        board = new Board(size);
        player1 = new Player("Player 1", new PlayingPieceX());
        player2 = new Player("Player 2", new PlayingPieceO());
        currentPlayer = player1;
    }

    public void startGame() {
        while (true) {
            board.printBoard();
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getPiece().getPieceType() + ")");
            int row = getValidInput("Enter row (0 to " + (board.getSize() - 1) + "): ");
            int col = getValidInput("Enter column (0 to " + (board.getSize() - 1) + "): ");

            if (board.placePiece(currentPlayer.getPiece(), row, col)) {
                if (board.checkWinner(currentPlayer.getPiece())) {
                    board.printBoard();
                    System.out.println(currentPlayer.getName() + " wins!");
                    break;
                }

                if (board.isBoardFull()) {
                    board.printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

                switchPlayer();
            } else {
                System.out.println("Cell already occupied, try again.");
            }
        }
    }

    private int getValidInput(String prompt) {
        int input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextInt();
            if (input >= 0 && input < board.getSize()) {
                break;
            } else {
                System.out.println("Invalid input, please enter a number between 0 and " + (board.getSize() - 1));
            }
        }
        return input;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3); // 3x3 board for standard Tic Tac Toe
        game.startGame();
    }
}
