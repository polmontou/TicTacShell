package fr.campus.model.games;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.view.Menu;
import fr.campus.view.UserInteraction;

public class Freestyle extends GameType{
    private final static int MAX_SIZE = 100;

    Freestyle() {
        super("Freestyle",0,0,0);
    }

    @Override
    public void init()
    {
        menu = new Menu();
        this.status = RoundEnd.NOTHING;
        this.players = menu.displayPlayerChoiceMenu();

        this.columnMax =  UserInteraction.askForInt("How many columns in your game? (choose between 1 and " + MAX_SIZE + "): ", 1, MAX_SIZE);
        this.lineMax = UserInteraction.askForInt("How many rows in your game? (choose between 1 and " + MAX_SIZE + "): ", 1, MAX_SIZE);
        this.winRule =  UserInteraction.askForInt("How many cells in a row to win the game?", 1, MAX_SIZE);
        this.board = new Board(lineMax,columnMax);
    }
}
