package fr.campus.model.player;

import fr.campus.controller.GameController;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, String pawn, GameController controller) {
        super(name, pawn, controller);
    }

    public int chooseInt(String message, int minValue, int maxValue){
        return controller.askForInt(message, minValue, maxValue);
    }

}
