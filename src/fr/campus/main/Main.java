package fr.campus.main;

import fr.campus.game.Game;

public class Main {
    public static void main(String[] args) {
            Menu menu = new Menu();
            Game game = menu.displayGameChoiceMenu();
            game.play();
    }
}