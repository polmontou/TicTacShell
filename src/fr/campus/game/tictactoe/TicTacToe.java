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

        winRule = UserInteraction.askForInt("How many cells in a row to win? (between 1 and "+board.getBoardSizeX()+") : ", 1, board.getBoardSizeX());

    }


    public void play() {
        int moveCount = 0;
        Player lastPlayer = null;
        RoundEnd results = null;

        do {
            View.displayBoard(board, board.getBoardSizeX());

            int currentIndex = moveCount%2;
            getMove(players[currentIndex]);
            lastPlayer = players[currentIndex];

            moveCount++;

            results = isOver(moveCount);
        } while (results == RoundEnd.NOTHING);

        View.displayBoard(board, board.getBoardSizeX());

        if (results.isWon()) {
            View.message(lastPlayer.getName() + " wins the game!");
        } else {
            View.message("It's a tie!");
        }
    }


    private RoundEnd isOver(int moveCount) {
        if (board.isWon(winRule)) {
            return RoundEnd.WIN;
        } else if (board.isFull(moveCount)) {
            return RoundEnd.TIE;
        } else {
            return RoundEnd.NOTHING;
        }


    }
    private boolean boardIsFull(int moveCount) {
        return moveCount == board.getBoardSizeX() * board.getBoardSizeX();
    }

    private void getMove(Player player) {
        int row;
        int col;
        View.message(player.getName()+"'s turn!\n");

        do {
            row = player.chooseInt("Choose a row between 1 and "+ board.getBoardSizeX() +" (integer expected) : ", 1, board.getBoardSizeX());
            col = player.chooseInt("Choose a column between 1 and "+ board.getBoardSizeX() +" (integer expected) : ",1, board.getBoardSizeX());
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
        if (row-1 < 0 || row-1 >= board.getBoardSizeX() || column-1 < 0 || column-1 >= board.getBoardSizeX()) {
            return false;
        }
        return true;
    }
}
