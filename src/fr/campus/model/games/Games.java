package fr.campus.model.games;

import fr.campus.controller.GameController;

public enum Games {
    TICTACTOE("TicTacToe"),
    PUISSANCE4("Puissance 4"),
    GOMOKU("Gomoku"),
    FREESTYLE ("Freestyle");
    
    private String name;

    Games(String name) {
        this.name = name;
    }

    public GameType createGame(GameController controller) {
        return GameFactory.createGame(this, controller);
    }
    public String toString() {
        return this.name;
    }
}
