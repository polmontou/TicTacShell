package fr.campus.main;

import fr.campus.game.Game;
import fr.campus.game.tictactoe.TicTacToe;

public class Main {
    public static void main(String[] args) {
            Menu menu = new Menu();
            Game game = menu.displayMainMenu();
            //game.init();
            game.play();
//        Game game = new Game(new TicTacToe(2));
//        game.play();
    }
}