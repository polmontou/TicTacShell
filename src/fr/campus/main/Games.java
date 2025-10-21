package fr.campus.main;

import fr.campus.game.Game;
import fr.campus.game.games.*;

public enum Games {
    TICTACTOE(new TicTacToe()),
    PUISSANCE4(new Puissance4()),
    GOMOKU(new Gomoku()),
    FREESTYLE (new Freestyle());
    
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
