package fr.campus.model.games;


import fr.campus.controller.GameController;

public class TicTacToe extends GameType {
    TicTacToe(GameController controller) {
        super("TicTacToe",3,3,3);
    };

}
