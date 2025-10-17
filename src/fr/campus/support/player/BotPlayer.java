package fr.campus.support.player;

public class BotPlayer extends Player {

    public BotPlayer(String name, String pawn) {
        super(name, pawn);
    }

    public int chooseInt(int maxValue){
        return (int)(Math.random() * maxValue);
    }
}
