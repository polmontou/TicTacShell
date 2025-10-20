package fr.campus.support.player;

import fr.campus.support.View;

public class BotPlayer extends Player {

    public BotPlayer(String name, String pawn) {
        super(name, pawn);
    }

    public int chooseInt(String message, int minValue, int maxValue){
        View.message(message);
        return (int)(Math.random() * maxValue) + 1 ;
    }
}
