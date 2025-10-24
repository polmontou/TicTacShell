package fr.campus.view;

import fr.campus.model.board.Board;
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

    public void displayBoard(Board board)
    {
        view.displayBoard(board);
    }

    public int askForInt(String message, int min, int max) {
        showLog(message);

        boolean exit = false;
        int answer = 0;

        answer = intVerification(exit, answer);


        while (answer < min || answer > max){

            showLog("Please enter a number between " + min + " and " + max+".");
            answer = intVerification(exit,answer);
        }

        return answer;
    }

    private int intVerification(boolean exit, int answer) {
        while(!exit) {
            try {
                answer = userInteraction.getUserInt();
                exit = true;
            } catch (InputMismatchException e) {
                showLog("Integer expected, try again :");
            }
        }
        return answer;
    }


    public void showLog(String message)
    {
        view.displayLog(message);
    }


}
