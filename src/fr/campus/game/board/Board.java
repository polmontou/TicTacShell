package fr.campus.game.board;

import fr.campus.support.UserInteraction;
import fr.campus.support.View;
import fr.campus.support.player.Player;

public class Board {
    private Cell[][] board;
    private final int MAX_SIZE = 10000;
    private int size;

    public Board()
    {
        int line = 6 ;
        int col = 7;
        board = new Cell[line][col];

        for(int i = 0; i < line; i++)
            {
                for(int j = 0; j < col; j++)
                {
                    board[i][j] = new Cell();
                }
            }
        View.message("Board created");
        View.message("The size is : " +line+ "x"+col+".");
    }

    public Board(int size) {

        this.size = size;
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
