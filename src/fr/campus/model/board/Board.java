package fr.campus.model.board;

import fr.campus.model.player.Player;

/**
 * Represents the game board.
 * Manages the grid of cells and checks for winning conditions.
 */
public class Board {

    private Cell[][] board;
    private int boardSizeY;
    private int boardSizeX;

    /**
     * Board constructor.
     * Initializes a board with the specified dimensions and creates empty cells.
     *
     * @param line The number of rows
     * @param column The number of columns
     */
    public Board(int line, int column) {
        boardSizeY = line;
        boardSizeX = column;

        board = new Cell[boardSizeY][boardSizeX];

        for(int i = 0; i < boardSizeY; i++) {
            for(int j = 0; j < boardSizeX; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * Checks if there is a winning condition on the board.
     * Tests all cells for horizontal, vertical, and diagonal wins.
     *
     * @param winRule The number of aligned cells needed to win
     * @return true if there is a winning line, false otherwise
     */
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

    /**
     * Checks if a cell is part of a winning diagonal.
     *
     * @param line The row index
     * @param col The column index
     * @param winRule The number of aligned cells needed to win
     * @return true if the cell is part of a winning diagonal, false otherwise
     */
    private boolean isInWinDiag(int line, int col, int winRule) {
        return checkDiag(line, col, 1,winRule) || checkDiag(line, col, -1,winRule);
    }

    /**
     * Checks for a diagonal win in a specific direction.
     *
     * @param line The starting row index
     * @param col The starting column index
     * @param gapLine The direction (1 for down-right, -1 for up-right)
     * @param winRule The number of aligned cells needed to win
     * @return true if there is a winning diagonal, false otherwise
     */
    private boolean checkDiag(int line, int col, int gapLine, int winRule) {
        int sameCellsInRow = 1;
        if ((gapLine > 0 && line + winRule > getBoardSizeY()) || (gapLine < 0 && (line - winRule + 1)< 0) || (col + winRule > getBoardSizeX())) return false;

        if (gapLine > 0) {
            int testLine = line;
            int testCol = col;
            while (board[testLine][testCol].getPlayer() == board[testLine + gapLine][testCol + 1].getPlayer()) {
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

    /**
     * Checks if a cell is part of a winning vertical line.
     *
     * @param line The row index
     * @param col The column index
     * @param winRule The number of aligned cells needed to win
     * @return true if the cell is part of a winning column, false otherwise
     */
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

    /**
     * Checks if a cell is part of a winning horizontal line.
     *
     * @param line The row index
     * @param col The column index
     * @param winRule The number of aligned cells needed to win
     * @return true if the cell is part of a winning row, false otherwise
     */
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

    /**
     * Checks if the board is completely filled.
     *
     * @param moveCount The total number of moves made
     * @return true if the board is full, false otherwise
     */
    public boolean isFull(int moveCount) {
        return moveCount == (getBoardSizeX() * getBoardSizeY());
    }

    /**
     * Updates a cell in a Connect 4 style game (gravity-based).
     * The piece falls to the lowest available position in the column.
     *
     * @param col The column number (1-indexed)
     * @param player The player making the move
     */
    public void updateCell(int col, Player player) {
        int currentLine = 5;

        while (!board[currentLine][col-1].isEmpty()) {
            currentLine--;
        }
        board[currentLine][col-1].setPlayer(player);
    }

    /**
     * Checks if a specific cell is available (empty).
     *
     * @param row The row number (1-indexed)
     * @param col The column number (1-indexed)
     * @return true if the cell is empty, false otherwise
     */
    public boolean checkCellAvailability(int row, int col) {
        return getCell(row-1,col-1).isEmpty();
    }

    /**
     * Checks if a column has at least one available cell.
     * Used for Connect 4 style games.
     *
     * @param col The column number (1-indexed)
     * @return true if the column has space, false if full
     */
    public boolean checkColumnAvailability(int col) {
        for(int i = 0; i < boardSizeY; i++) {
            if(getCell(i,col-1).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates a specific cell with a player's move.
     *
     * @param row The row number (1-indexed)
     * @param col The column number (1-indexed)
     * @param player The player making the move
     */
    public void updateCell(int row, int col, Player player) {
        board[row-1][col-1].setPlayer(player);
    }

    /**
     * Gets a specific cell from the board.
     *
     * @param row The row index (0-indexed)
     * @param col The column index (0-indexed)
     * @return The cell at the specified position
     */
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    /**
     * Gets the number of columns on the board.
     *
     * @return The board width
     */
    public int getBoardSizeX() {
        return boardSizeX;
    }

    /**
     * Gets the number of rows on the board.
     *
     * @return The board height
     */
    public int getBoardSizeY() {
        return boardSizeY;
    }
}