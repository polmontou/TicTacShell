package fr.campus.main;

import fr.campus.game.Game;
import fr.campus.support.Tool;
import fr.campus.support.player.Player;

import static java.lang.String.valueOf;

public class Menu {

    public Game displayMainMenu() {
        Tool.message("\n-*- Welcome in your games' library -*-");
        Tool.message("\nYou can play :");

        Games[] list = Games.values();

        for (int i = 0; i < list.length; i++) {
            Tool.message((i+1) + " - " + list[i].toString());
        }

        int choice = Tool.askForInt("\nWhich one do you wanna play? (integer expected) : ", 1, list.length);
        Games wantedGame = Tool.parseUserChoice(choice, Games.class);

        return wantedGame.getGame();
    }

    public Player[] displayPlayerChoiceMenu() {
        Tool.message("\nModes available :"+
                "\n1 - 2 real players" +
                "\n2 - 1 real vs 1 bot" +
                "\n3 - 2 bots");
        int choice = Tool.askForInt("Which one do you wanna play? (integer expected) : ", 1, 3);

        Player[] players = Tool.parseUserPlayerChoice(choice);

        return players;
    }



}
