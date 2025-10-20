package fr.campus.game.tictactoe;


import fr.campus.game.GameType;
import fr.campus.game.RoundEnd;
import fr.campus.game.board.Board;
import fr.campus.main.Menu;
import fr.campus.support.View;
import fr.campus.support.player.Player;
import fr.campus.support.UserInteraction;

public class TicTacToe extends GameType {

    private final int MAX_SIZE = 10000;

    private Board board;
    private final static int PLAYER_LIMIT = 2;
    private Menu menu;

    public TicTacToe() {
        super("TicTacToe");
    };


    @Override
    public void init() {
        super.init();

        int size = UserInteraction.askForInt("What size do you want for your game? (between 1 and "+MAX_SIZE+") : ", 1, MAX_SIZE);
        board = new Board(size);

        winRule = UserInteraction.askForInt("How many cells in a row to win? (between 1 and "+board.getBoardSize()+") : ", 1, board.getBoardSize());

    }


    public void play() {
        int moveCount = 0;
        Player lastPlayer = null;
        RoundEnd results = null;

        do {
            View.displayBoard(board, board.getBoardSize());

            int currentIndex = moveCount%2;
            getMove(players[currentIndex]);
            lastPlayer = players[currentIndex];

            moveCount++;

            results = isOver(moveCount);
        } while (results == RoundEnd.NOTHING);

        View.displayBoard(board, board.getBoardSize());

        if (results.isWon()) {
            View.message(lastPlayer.getName() + " wins the game!");
        } else {
            View.message("It's a tie!");
        }
    }


    private RoundEnd isOver(int moveCount) {
        if (isWon()) {
            return RoundEnd.WIN;
        } else if (board.isFull(moveCount)) {
            return RoundEnd.TIE;
        } else {
            return RoundEnd.NOTHING;
        }


    }
    private boolean boardIsFull(int moveCount) {
        return moveCount == board.getBoardSize() * board.getBoardSize();
    }

    private boolean isWon() {
        for (int line = 0; line < board.getBoardSize(); line++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
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
        if ((gapLine > 0 && line + winRule > board.getBoardSize()) || (gapLine < 0 && (line - winRule + 1)< 0) || (col + winRule > board.getBoardSize())) return false;

        if (gapLine > 0) {
            int testLine = line;
            int testCol = col;
            while (testLine + gapLine < board.getBoardSize() && testCol + 1 < board.getBoardSize() && board.getCell(testLine,testCol).getPlayer() == board.getCell(testLine + gapLine,testCol + 1).getPlayer()) {
                sameCellsInRow++;
                testLine++;
                testCol++;
                if (sameCellsInRow == winRule) {
                    return true;
                }
            }
        } else {
            while (line - 1 >= 0 && col + 1 < board.getBoardSize() && board.getCell(line,col).getPlayer() == board.getCell(line - 1,col + 1).getPlayer()) {
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
        if (line + winRule >= board.getBoardSize()) return false;

        int sameCellsInRow = 1;
        while (line + 1 < board.getBoardSize() && board.getCell(line,col).getPlayer() == board.getCell(line+1,col).getPlayer()) {
            sameCellsInRow++;
            line++;
            if (sameCellsInRow == winRule) {
                return true;
            }
        }
        return false;
    }

    private boolean isInWinLine(int line, int col) {
        if (col + winRule >= board.getBoardSize()) return false;

        int sameCellsInRow = 1;
        while (col + 1 < board.getBoardSize() && board.getCell(line,col).getPlayer() == board.getCell(line,col + 1).getPlayer()) {
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
            row = player.chooseInt("Choose a row between 1 and "+ board.getBoardSize() +" (integer expected) : ", 1, board.getBoardSize());
            col = player.chooseInt("Choose a column between 1 and "+ board.getBoardSize() +" (integer expected) : ",1, board.getBoardSize());
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
        if (row-1 < 0 || row-1 >= board.getBoardSize() || column-1 < 0 || column-1 >= board.getBoardSize()) {
            return false;
        }
        return true;
    }
}
