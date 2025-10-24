package fr.campus.view;

import fr.campus.model.games.Games;

import java.util.InputMismatchException;

public class Menu {
private View view;
private UserInteraction userInteraction;
    public void displayGameChoiceMenu() {
        showLog("\n-*- Welcome in your games' library -*-");
        showLog("\nYou can play at :");

        Games[] list = Games.values();

        for (int i = 0; i < list.length; i++) {
            showLog((i+1) + " - " + list[i].toString());
        }
        showLog("");
    }

    public void displayPlayerChoiceMenu() {
        showLog("\nModes available :"+
                "\n1 - 2 real players" +
                "\n2 - 1 real vs 1 bot" +
                "\n3 - 2 bots");
    }

    int askForInt(String message, int min, int max) {
        showLog(message);

        while(!exit) {
            try {
                answer = sc.nextInt();
                exit = true;
            } catch (InputMismatchException e) {
                sc.nextLine();
                showLog(("Integer expected, try again :");
            }
        }

        int answer = userInteraction.getUserInt();


        while (answer < min || answer > max){
            showLog("Please enter a number between " + min + " and " + max+".");
            answer = userInteraction.getUserInt();
        }

        return answer;
    }


    public void showLog(String message)
    {
        view.displayLog(message);
    }


}
