package fr.campus.main;

import fr.campus.game.tictactoe.TicTacToe;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new TicTacToe(2));
        game.play();
    }
}