package fr.campus.game;


import fr.campus.support.Cell;
import fr.campus.support.Player;
import fr.campus.support.Tool;

import java.util.Objects;

public class TicTacToe extends GameType {
    private int boardSize;
    private Cell[][] board;


    public TicTacToe(int boardSize) {
        super("Tic-Tac-Toe");
        this.boardSize = boardSize;
        this.board = new Cell[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Cell();
            }
        }
    };

    void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < boardSize; i++) {
            System.out.print("|");
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j].toString()+"|");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public void play(Player player) {
        for (int i = 0; i < 3; i++) {
            displayBoard();
            getMove(player);
        }
    }

    private void getMove(Player player) {
        int row;
        int col;
        do {
            Tool.message("Choose a row between 1 and 3 (integer expected) : ");
            row = Tool.getUserInt();
            Tool.message("Choose a column between 1 and 3 (integer expected) : ");
            col = Tool.getUserInt();
        } while (!checkMove(row, col));
        updateCell(row, col, player);
    }

    private void updateCell(int row, int col, Player player) {
        board[row-1][col-1].setPlayer(player);
    }

    private boolean checkMove(int row, int column) {
        if (checkRange(row, column)) {
            if (checkCellAvailability(row, column)) {
                return true;
            }
            Tool.message("Cell not empty, try again!");
        } else {
            Tool.message("You're out of bounds!");
        }
        return false;
    }

    private boolean checkCellAvailability(int row, int col) {
        return Objects.equals(board[row-1][col-1].toString(), "   ");
    }
    private boolean checkRange(int row, int column) {
        if (row-1 < 0 || row-1 >= boardSize || column-1 < 0 || column-1 >= boardSize) {
            return false;
        }
        return true;
    }
}
