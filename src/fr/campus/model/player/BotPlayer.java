package fr.campus.model.player;

import fr.campus.controller.GameController;
import fr.campus.view.View;

public class BotPlayer extends Player {

    public BotPlayer(String name, String pawn, GameController controller) {
        super(name, pawn, controller);
    }

    public int chooseInt(String message, int minValue, int maxValue){
        return (int)(Math.random() * maxValue) + 1 ;
    }
}
