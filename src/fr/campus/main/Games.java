package fr.campus.main;

import fr.campus.game.Game;
import fr.campus.game.GameType;
import fr.campus.game.puissance4.Puissance4;
import fr.campus.game.tictactoe.TicTacToe;

public enum Games {
    TICTACTOE(new TicTacToe()),
    PUISSANCE4(new Puissance4());

    private String name;
    private Game game;

    Games(GameType chosenGame) {
        this.name = chosenGame.getName();
        game = new Game(chosenGame);
    }

    public Game getGame() {
        return game;
    }

    public String toString() {
        return this.name;
    }
}
