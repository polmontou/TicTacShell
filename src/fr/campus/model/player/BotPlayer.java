package fr.campus.model.player;

import fr.campus.view.View;

public class BotPlayer extends Player {

    public BotPlayer(String name, String pawn) {
        super(name, pawn);
    }

    public int chooseInt(String message, int minValue, int maxValue){
        //View.message(message);
        return (int)(Math.random() * maxValue) + 1 ;
    }
}
