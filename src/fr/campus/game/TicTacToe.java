package fr.campus.game;


import fr.campus.support.Cell;
import fr.campus.support.Player;
import fr.campus.support.Tool;

import java.util.Objects;

public class TicTacToe extends GameType {
    private int boardSize;
    private Cell[][] board;


    public TicTacToe(int boardSize) {
        super("TicTacToe");
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

    public void play(Player player1, Player player2) {
        int i = 0;
        Player lastPlayer = null;

        distributePawn(player1, player2);

        while (!isOver() && i < 8) {
            displayBoard();
            if(i%2 == 0) {
                getMove(player1);
                lastPlayer = player1;
            } else {
                getMove(player2);
                lastPlayer = player2;
            }
            i++;

        }
        displayBoard();

        if (isOver()) {
            Tool.message(lastPlayer.getName()+" wins!");
        } else {
            Tool.message("It's a tie!");
        }


    }

    private boolean isOver() {
        //Checking row
        for (int i = 0; i < boardSize; i++) {
            if(board[i][0].getPlayer() == board[i][1].getPlayer() && board[i][0].getPlayer() == board[i][2].getPlayer() && !board[i][0].toString().equals("   ")) {
                return true;
            }
        }
        //Checking column
        for (int j = 0; j < boardSize; j++) {
            if(board[0][j].getPlayer() == board[1][j].getPlayer() && board[0][j].getPlayer() == board[2][j].getPlayer() && !board[0][j].toString().equals("   ")) {
                return true;
            }
        }
        //Checking diagonal
        if((board[0][0].getPlayer() == board[1][1].getPlayer() && board[0][0].getPlayer() == board[2][2].getPlayer() && !board[0][0].toString().equals("   "))
        || board[0][2].getPlayer() == board[1][1].getPlayer() && board[0][2].getPlayer() == board[2][0].getPlayer() && !board[0][2].toString().equals("   ")) {
           return true;
        }

        return false;
    }

    private void distributePawn(Player player1, Player player2) {
        int i = (int)(Math.random()*2);
        switch (i) {
            case 0:
                player1.setPawn("X");
                player2.setPawn("O");
            case 1:
                player1.setPawn("O");
                player2.setPawn("X");
        }
    }

    private void getMove(Player player) {
        int row;
        int col;
        Tool.message("It's "+player.getName()+"'s turn!");
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
        return board[row-1][col-1].toString().equals("   ");
    }

    private boolean checkRange(int row, int column) {
        if (row-1 < 0 || row-1 >= boardSize || column-1 < 0 || column-1 >= boardSize) {
            return false;
        }
        return true;
    }
}
