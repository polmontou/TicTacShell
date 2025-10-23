package fr.campus.view;

import fr.campus.model.games.Games;

public class Menu {

    public int displayGameChoiceMenu() {
        View.message("\n-*- Welcome in your games' library -*-");
        View.message("\nYou can play at :");

        Games[] list = Games.values();

        for (int i = 0; i < list.length; i++) {
            View.message((i+1) + " - " + list[i].toString());
        }

        return UserInteraction.askForInt("\nWhich one do you wanna play? (integer expected) : ", 1, list.length);
    }

    public int displayPlayerChoiceMenu() {
        View.message("\nModes available :"+
                "\n1 - 2 real players" +
                "\n2 - 1 real vs 1 bot" +
                "\n3 - 2 bots");
        return UserInteraction.askForInt("Which one do you wanna play? (integer expected) : ", 1, 3);
    }




}
