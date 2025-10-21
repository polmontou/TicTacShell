package fr.campus.game.board;

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

    public boolean isInWinCol(int line, int col, int winRule) {

        int colMax = getBoardSizeY();
        if (line + winRule >= colMax) return false;

        int sameCellsInRow = 1;
        while (line + 1 < colMax && board[line][col].getPlayer() == board[line+1][col].getPlayer()) {
            sameCellsInRow++;
            line++;
            if (sameCellsInRow == winRule) {
                return true;
            }
        }
        return false;
    }


    public boolean isInWinLine(int line, int col, int winRule) {

        int RowSize = getBoardSizeX();

        if (col + winRule >= RowSize) return false;

        int sameCellsInRow = 1;
        while (col + 1 < RowSize && board[line][col].getPlayer() == board[line][col + 1].getPlayer()) {
            sameCellsInRow++;
            col++;
            if (sameCellsInRow == winRule) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull(int moveCount)
    {
        return moveCount == (size * size);
    }

    public boolean isFull(int moveCount,int col, int line)
    {
        return moveCount == (col * line);
    }

    public void updateCell(int col, Player player)
    {
        int currentLine = 5 ;

        while (!board[currentLine][col-1].isEmpty())
        {
            currentLine--;
        }
        board[currentLine][col-1].setPlayer(player);
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

    public int getBoardSizeX() {
        return board[0].length;
    }

    public int getBoardSizeY()
    {
        return board.length;
    }
}
