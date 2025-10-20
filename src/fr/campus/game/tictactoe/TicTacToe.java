package fr.campus.game.tictactoe;


import fr.campus.game.GameType;
import fr.campus.main.Menu;
import fr.campus.support.Cell;
import fr.campus.support.View;
import fr.campus.support.player.Player;
import fr.campus.support.UserInteraction;

public class TicTacToe extends GameType {
    private Player[] players;
    private final int MAX_SIZE = 10000;
    private static int boardSize;
    private static int winRule = 4;
    private Cell[][] board;
    private final static int PLAYER_LIMIT = 2;
    private Menu menu;

    public TicTacToe() {
        super("TicTacToe");
    };

    public void displayBoard() {

    }


    public void init() {
        menu = new Menu();

        this.players = menu.displayPlayerChoiceMenu();

        boardSize = UserInteraction.askForInt("What size do you want for your game? (between 1 and "+MAX_SIZE+") : ", 1, MAX_SIZE);
        board = new Cell[boardSize][boardSize];

        winRule = UserInteraction.askForInt("How many cells in a row to win? (between 1 and "+boardSize+") : ", 1, boardSize);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Cell();
            }
        }


        View.message("Your board is "+boardSize+"x"+boardSize+".\nYou have to align "+winRule+" cells to win the game.\n");
    }
    public void play() {

        int moveCount = 0;
        Player lastPlayer = null;
        TicTacToeRoundEnd results = null;

        do {
            View.displayBoard(board, boardSize);

            int currentIndex = moveCount%2;
            getMove(players[currentIndex]);
            lastPlayer = players[currentIndex];

            moveCount++;

            results = isOver(moveCount);
        } while (results == TicTacToeRoundEnd.NOTHING);

        View.displayBoard(board, boardSize);

        if (results.isWon()) {
            View.message(lastPlayer.getName() + " wins the game!");
        } else {
            View.message("It's a tie!");
        }
    }

    private TicTacToeRoundEnd isOver(int moveCount) {
        if (isWon()) {
            return TicTacToeRoundEnd.WIN;
        } else if (boardIsFull(moveCount)) {
            return TicTacToeRoundEnd.TIE;
        } else {
            return TicTacToeRoundEnd.NOTHING;
        }


    }
    private boolean boardIsFull(int moveCount) {
        return moveCount == boardSize * boardSize;
    }

    private boolean isWon() {
        for (int line = 0; line < boardSize; line++) {
            for (int col = 0; col < boardSize; col++) {
                if (!board[line][col].isEmpty()) {
                    if (isInWinLine(line, col) || isInWinCol(line, col) || isInWinDiag(line, col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isInWinDiag(int line, int col) {
        return checkDiag(line, col, 1) || checkDiag(line, col, -1);
    }

    private boolean checkDiag(int line, int col, int gapLine) {
        int sameCellsInRow = 1;
        if ((gapLine > 0 && line + winRule > boardSize) || (gapLine < 0 && (line - winRule + 1)< 0) || (col + winRule > boardSize)) return false;

        if (gapLine > 0) {
            int testLine = line;
            int testCol = col;
            while (testLine + gapLine < boardSize && testCol + 1 < boardSize && board[testLine][testCol].getPlayer() == board[testLine + gapLine][testCol + 1].getPlayer()) {
                sameCellsInRow++;
                testLine++;
                testCol++;
                if (sameCellsInRow == winRule) {
                    return true;
                }
            }
        } else {
            while (line - 1 >= 0 && col + 1 < boardSize && board[line][col].getPlayer() == board[line - 1][col + 1].getPlayer()) {
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

    private boolean isInWinCol(int line, int col) {
        if (line + winRule >= boardSize) return false;

        int sameCellsInRow = 1;
        while (line + 1 < boardSize && board[line][col].getPlayer() == board[line+1][col].getPlayer()) {
            sameCellsInRow++;
            line++;
            if (sameCellsInRow == winRule) {
                return true;
            }
        }
        return false;
    }

    private boolean isInWinLine(int line, int col) {
        if (col + winRule >= boardSize) return false;

        int sameCellsInRow = 1;
        while (col + 1 < boardSize && board[line][col].getPlayer() == board[line][col + 1].getPlayer()) {
            sameCellsInRow++;
            col++;
            if (sameCellsInRow == winRule) {
                return true;
            }
        }
        return false;
    }

    private void getMove(Player player) {
        int row;
        int col;
        View.message(player.getName()+"'s turn!\n");

        do {
            row = player.chooseInt("Choose a row between 1 and "+ boardSize +" (integer expected) : ", 1, boardSize);
            col = player.chooseInt("Choose a column between 1 and "+ boardSize +" (integer expected) : ",1, boardSize);
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
            View.message("Cell not empty, try again!");
        } else {
            View.message("You're out of bounds!");
        }
        return false;
    }

    private boolean checkCellAvailability(int row, int col) {
        return board[row-1][col-1].isEmpty();
    }

    private boolean checkRange(int row, int column) {
        if (row-1 < 0 || row-1 >= boardSize || column-1 < 0 || column-1 >= boardSize) {
            return false;
        }
        return true;
    }
}
