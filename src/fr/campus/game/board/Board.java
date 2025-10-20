package fr.campus.game.board;

import fr.campus.support.UserInteraction;
import fr.campus.support.View;
import fr.campus.support.player.Player;

public class Board {
    private Cell[][] board;
    private final int MAX_SIZE = 10000;
    private int size;

    private void Board() {
        size = UserInteraction.askForInt("What size do you want for your game? (between 1 and "+MAX_SIZE+") : ", 1, MAX_SIZE);
        board = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }

        View.message("Your board is "+ size +"x"+ size +".");
    }

    public void updateCell(int row, int col, Player player) {
        board[row-1][col-1].setPlayer(player);
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public int getBoardSize() {
        return size;
    }
}
