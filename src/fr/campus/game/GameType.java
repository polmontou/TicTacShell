package fr.campus.game;

import fr.campus.support.Player;

public abstract class GameType {
    String name;

    public GameType(String name) {
        this.name = name;
    }

    abstract void displayBoard();
    public abstract void play(Player player);
}
