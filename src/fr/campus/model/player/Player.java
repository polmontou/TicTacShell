package fr.campus.model.player;

import fr.campus.controller.GameController;

public abstract class Player {
    private String name;
    private String pawn;
    protected GameController controller;

    public Player(String name, String pawn, GameController controller) {
        this.name = name;
        this.pawn = pawn;
        this.controller = controller;
    }

    public abstract int chooseInt(String message, int minValue, int maxValue);

    //Getter
    public String getName() {
        return name;
    }
    public String getPawn() {
        return pawn;
    }

}
