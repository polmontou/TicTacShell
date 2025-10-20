package fr.campus.game;

import fr.campus.game.board.Board;
import fr.campus.main.Menu;
import fr.campus.support.UserInteraction;
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



    public String getName() {
        return name;
    }
    public abstract void play();
}
