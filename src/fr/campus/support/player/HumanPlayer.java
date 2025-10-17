package fr.campus.support.player;

import fr.campus.support.Tool;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, String pawn) {
        super(name, pawn);
    }

    public int chooseInt(int maxValue){
        return Tool.getUserInt();
    }

}
