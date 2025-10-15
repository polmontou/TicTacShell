package fr.campus.main;

import fr.campus.game.GameType;
import fr.campus.game.TicTacToe;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new TicTacToe(10));
        game.play();
    }
}