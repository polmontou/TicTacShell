package fr.campus.model.games;

import fr.campus.controller.GameController;

public class Gomoku extends GameType{
    Gomoku(GameController controller) {
        super("Gomoku",5,15,15);
    };
}
