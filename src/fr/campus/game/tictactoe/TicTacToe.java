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

    protected void getMove(Player player) {
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
