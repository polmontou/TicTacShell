package fr.campus;

import fr.campus.controller.GameController;

public class Main {
    public static void main(String[] args) {
        GameController game = new GameController();
        game.interact();
    }
}