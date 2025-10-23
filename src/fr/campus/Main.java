package fr.campus;

import fr.campus.controller.GameController;
import fr.campus.view.Menu;

public class Main {
    public static void main(String[] args) {
        GameController game = new GameController();
        game.initGame();
        game.play();
    }
}