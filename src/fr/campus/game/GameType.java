package fr.campus.game;

import fr.campus.game.board.Board;
import fr.campus.main.Menu;
import fr.campus.support.UserInteraction;
import fr.campus.support.View;
import fr.campus.support.player.Player;

public abstract class GameType {
    String name;

    protected Player[] players;
    protected Board board;
    protected Menu menu;
    protected static int winRule;

    public GameType(String name) {
        this.name = name;
    }

    public void init() {
        menu = new Menu();

        this.players = menu.displayPlayerChoiceMenu();
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
