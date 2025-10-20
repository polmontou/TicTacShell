package fr.campus.game.tictactoe;


import fr.campus.game.GameType;
import fr.campus.game.RoundEnd;
import fr.campus.game.board.Board;
import fr.campus.main.Menu;
import fr.campus.support.View;
import fr.campus.support.player.Player;
import fr.campus.support.UserInteraction;

public class TicTacToe extends GameType {
    private Player[] players;
    private final int MAX_SIZE = 10000;
    private static int boardSize;
    private static int winRule;
    private Board board;
    private final static int PLAYER_LIMIT = 2;
    private Menu menu;

    public TicTacToe() {
        super("TicTacToe");
    };

    public void init() {
        menu = new Menu();

        this.players = menu.displayPlayerChoiceMenu();

        board = new Board();

        winRule = UserInteraction.askForInt("How many cells in a row to win? (between 1 and "+board.getBoardSize()+") : ", 1, board.getBoardSize());
    }



    public void play() {
        int moveCount = 0;
        Player lastPlayer = null;
        RoundEnd results = null;

        do {
            View.displayBoard(board, boardSize);

            int currentIndex = moveCount%2;
            getMove(players[currentIndex]);
            lastPlayer = players[currentIndex];

            moveCount++;

            results = isOver(moveCount);
        } while (results == RoundEnd.NOTHING);

        View.displayBoard(board, boardSize);

        if (results.isWon()) {
            View.message(lastPlayer.getName() + " wins the game!");
        } else {
            View.message("It's a tie!");
        }
    }

    private RoundEnd isOver(int moveCount) {
        if (isWon()) {
            return RoundEnd.WIN;
        } else if (boardIsFull(moveCount)) {
            return RoundEnd.TIE;
        } else {
            return RoundEnd.NOTHING;
        }


    }
    private boolean boardIsFull(int moveCount) {
        return moveCount == boardSize * boardSize;
    }

    private boolean isWon() {
        for (int line = 0; line < boardSize; line++) {
            for (int col = 0; col < boardSize; col++) {
                if (!board.getCell(line,col).isEmpty()) {
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
            while (testLine + gapLine < boardSize && testCol + 1 < boardSize && board.getCell(testLine,testCol).getPlayer() == board.getCell(testLine + gapLine,testCol + 1).getPlayer()) {
                sameCellsInRow++;
                testLine++;
                testCol++;
                if (sameCellsInRow == winRule) {
                    return true;
                }
            }
        } else {
            while (line - 1 >= 0 && col + 1 < boardSize && board.getCell(line,col).getPlayer() == board.getCell(line - 1,col + 1).getPlayer()) {
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
        while (line + 1 < boardSize && board.getCell(line,col).getPlayer() == board.getCell(line+1,col).getPlayer()) {
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
        while (col + 1 < boardSize && board.getCell(line,col).getPlayer() == board.getCell(line,col + 1).getPlayer()) {
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
        board.updateCell(row, col, player);
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
        return board.getCell(row-1,col-1).isEmpty();
    }

    private boolean checkRange(int row, int column) {
        if (row-1 < 0 || row-1 >= boardSize || column-1 < 0 || column-1 >= boardSize) {
            return false;
        }
        return true;
    }
}
