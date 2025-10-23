package fr.campus.model.board;

import fr.campus.view.View;
import fr.campus.model.player.Player;

public class Board {
    private Cell[][] board;

   private int boardSizeY;
   private int boardSizeX;


    public Board(int line, int column)
    {
        boardSizeY = line;
        boardSizeX = column;

        board = new Cell[boardSizeY][boardSizeX];

        for(int i = 0; i < boardSizeY; i++)
            {
                for(int j = 0; j < boardSizeX; j++)
                {
                    board[i][j] = new Cell();
                }
            }
        //View.message("Board created");
        //View.message("The size is : " +boardSizeY+ "x"+boardSizeX+".");
    }
    


    public boolean isWon(int winRule) {
        for (int line = 0; line < getBoardSizeY(); line++) {
            for (int col = 0; col < getBoardSizeX(); col++) {
                if (!board[line][col].isEmpty()) {
                    if (isInWinLine(line, col,winRule) || isInWinCol(line, col,winRule) || isInWinDiag(line, col,winRule)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isInWinDiag(int line, int col,  int winRule) {
        return checkDiag(line, col, 1,winRule) || checkDiag(line, col, -1,winRule);
    }

    private boolean checkDiag(int line, int col, int gapLine, int winRule) {
        int sameCellsInRow = 1;
        if ((gapLine > 0 && line + winRule > getBoardSizeY()) || (gapLine < 0 && (line - winRule + 1)< 0) || (col + winRule > getBoardSizeX())) return false;

        if (gapLine > 0) {
            int testLine = line;
            int testCol = col;
            while (board[testLine][testCol].getPlayer() == board[testLine + gapLine][testCol + 1].getPlayer())
            {
                sameCellsInRow++;
                testLine++;
                testCol++;
                if (sameCellsInRow == winRule) {
                    return true;
                }
            }
        } else {
            while (board[line][col].getPlayer() == board[line - 1][col + 1].getPlayer()) {
                sameCellsInRow++;
                line--;
                col++;
                if (sameCellsInRow == winRule) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInWinCol(int line, int col, int winRule) {

        int colMax = getBoardSizeY();
        if (line + winRule > colMax) return false;

        int sameCellsInRow = 1;
        while (board[line][col].getPlayer() == board[line+1][col].getPlayer()) {
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

        if (col + winRule > RowSize) return false;

        int sameCellsInRow = 1;
        while (board[line][col].getPlayer() == board[line][col + 1].getPlayer()) {
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
        return moveCount == (getBoardSizeX() * getBoardSizeY());
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


    public int getBoardSizeX() {
        return boardSizeX;
    }

    public int getBoardSizeY()
    {
        return boardSizeY;
    }
}
