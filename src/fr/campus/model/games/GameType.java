package fr.campus.model.games;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.view.Menu;
import fr.campus.view.View;
import fr.campus.model.player.Player;

public abstract class GameType {
    protected String name;
    protected Player[] players;
    protected Board board;
    protected Menu menu;
    protected  int winRule;
    protected  int lineMax;
    protected  int columnMax;
    protected RoundEnd status;

    public GameType(String name, int winRule, int lineMax, int columnMax) {
        this.name = name;
        this.winRule = winRule;
        this.lineMax = lineMax;
        this.columnMax = columnMax;
    }

    public void init() {
        menu = new Menu();
        //this.players = menu.displayPlayerChoiceMenu();
        board = new Board(lineMax, columnMax);
        this.status = RoundEnd.NOTHING;
    }


    private RoundEnd isOver (int moveCount)
    {
        if(board.isWon(winRule))
        {
            status = RoundEnd.WIN;
            return status;
        }
        else if(board.isFull(moveCount))
        {
            status = RoundEnd.TIE;
            return status;
        }
        return status;
    }

    public void play()
    {
        int moveCount = 0;
        Player lastPlayer = null;
        RoundEnd results = null;

        do {
            View.displayBoard(board);

            int currentIndex = moveCount%2;
            getMove(players[currentIndex]);
            lastPlayer = players[currentIndex];

            moveCount++;

            results = isOver(moveCount);
        } while (results == RoundEnd.NOTHING);

        View.displayBoard(board);

        if (results.isWon()) {
            View.message(lastPlayer.getName() + " wins the game!");
        } else {
            View.message("It's a tie!");
        }
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

    public String getName() {
        return name;
    }
}
