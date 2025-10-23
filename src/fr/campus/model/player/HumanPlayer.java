package fr.campus.model.player;

import fr.campus.view.UserInteraction;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, String pawn) {
        super(name, pawn);
    }

    public int chooseInt(String message, int minValue, int maxValue){
        return 1;//return UserInteraction.askForInt(message, minValue, maxValue);
    }

}
