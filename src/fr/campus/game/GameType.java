package fr.campus.game;

public abstract class GameType {
    String name;

    public GameType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    protected abstract void displayBoard();
    public abstract void play();
}
