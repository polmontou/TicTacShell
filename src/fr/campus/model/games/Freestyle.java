package fr.campus.model.games;

import fr.campus.controller.GameController;
import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.model.player.Player;
import fr.campus.view.UserInteraction;

public class Freestyle extends GameType{
    private final static int MAX_SIZE = 100;
    private GameController controller;

    Freestyle(GameController controller) {
        super("Freestyle",0,0,0);
        this.controller = controller;
    }

    @Override
    public void init(Player[] players)
    {
        this.status = RoundEnd.NOTHING;
        this.players = players;

        this.columnMax =  controller.askForInt("How many columns in your game? (choose between 1 and " + MAX_SIZE + "): ", 1, MAX_SIZE);
        this.lineMax = controller.askForInt("How many rows in your game? (choose between 1 and " + MAX_SIZE + "): ", 1, MAX_SIZE);
        this.winRule =  controller.askForInt("How many cells in a row to win the game?", 1, MAX_SIZE);
        this.board = new Board(lineMax,columnMax);
    }
}
