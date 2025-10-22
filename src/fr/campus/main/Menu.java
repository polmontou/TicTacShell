package fr.campus.main;

import fr.campus.game.Game;
import fr.campus.support.UserInteraction;
import fr.campus.support.View;
import fr.campus.support.player.Player;

public class Menu {

    public Game displayGameChoiceMenu() {
        View.message("\n-*- Welcome in your games' library -*-");
        View.message("\nYou can play at :");

        Games[] list = Games.values();

        for (int i = 0; i < list.length; i++) {
            View.message((i+1) + " - " + list[i].toString());
        }

        int choice = UserInteraction.askForInt("\nWhich one do you wanna play? (integer expected) : ", 1, list.length);
        Games wantedGame = UserInteraction.parseUserChoice(choice, Games.class);

        return wantedGame.getGame();
    }

    public Player[] displayPlayerChoiceMenu() {
        View.message("\nModes available :"+
                "\n1 - 2 real players" +
                "\n2 - 1 real vs 1 bot" +
                "\n3 - 2 bots");
        int choice = UserInteraction.askForInt("Which one do you wanna play? (integer expected) : ", 1, 3);

        Player[] players = UserInteraction.parseUserPlayerChoice(choice);

        return players;
    }



}
