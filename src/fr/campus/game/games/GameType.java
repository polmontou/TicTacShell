package fr.campus.game.games;

import fr.campus.game.RoundEnd;
import fr.campus.game.board.Board;
import fr.campus.main.Menu;
import fr.campus.support.View;
import fr.campus.support.player.Player;

public abstract class GameType {
    String name;

    protected Player[] players;
    protected Board board;
    protected Menu menu;
    protected  int winRule;

    protected  int lineMax;
    protected  int columnMax;

    public GameType(String name, int winRule, int lineMax, int columnMax) {
        this.name = name;
        this.winRule = winRule;
        this.lineMax = lineMax;
        this.columnMax = columnMax;
    }

    public void init() {
        menu = new Menu();
        this.players = menu.displayPlayerChoiceMenu();
        board = new Board(lineMax, columnMax);
    }


    private RoundEnd isOver (int moveCount)
    {
        if(board.isWon(winRule))
        {
            return RoundEnd.WIN;
        }
        else if(board.isFull(moveCount))
        {
            return RoundEnd.TIE;
        }
        return RoundEnd.NOTHING;
    }


    protected abstract void getMove(Player player);
    public String getName() {
        return name;
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
}
