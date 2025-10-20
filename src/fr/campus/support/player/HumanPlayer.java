package fr.campus.support.player;

import fr.campus.support.UserInteraction;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, String pawn) {
        super(name, pawn);
    }

    public int chooseInt(String message, int minValue, int maxValue){
        return UserInteraction.askForInt(message, minValue, maxValue);
    }

}
