package fr.campus.view;

import fr.campus.model.board.Board;
import fr.campus.model.games.GamesPreset;

import java.util.InputMismatchException;

/**
 * Menu class for managing user interactions and display.
 * Acts as an intermediary between the controller and the view components.
 * Handles input validation and menu displays.
 */
public class Menu {

    private View view;
    private UserInteraction userInteraction;

    /**
     * Menu constructor.
     * Initializes the view and user interaction components.
     */
    public Menu() {
        this.view = new View();
        this.userInteraction = new UserInteraction();
    }

    /**
     * Displays the game choice menu.
     * Shows available games and the quit option.
     */
    public void displayGameChoiceMenu() {
        showLog("\n-*- Welcome in your games' library -*-");
        showLog("\nYou can play at :");

        GamesPreset[] list = GamesPreset.values();

        for (int i = 0; i < list.length; i++) {
            showLog((i+1) + " - " + list[i].toString());
        }
        showLog(list.length+1 +" - Quit");
        showLog("");
    }

    /**
     * Displays the player mode choice menu.
     * Shows available game modes (human vs human, human vs bot, bot vs bot).
     */
    public void displayPlayerChoiceMenu() {
        showLog("\nModes available :"+
                "\n1 - 2 real players" +
                "\n2 - 1 real vs 1 bot" +
                "\n3 - 2 bots"+
                "\n4 - Quit");
    }

    /**
     * Displays the current state of the game board.
     * Delegates to the view component for rendering.
     *
     * @param board The game board to display
     */
    public void displayBoard(Board board) {
        view.displayBoard(board);
    }

    /**
     * Asks the user for an integer input within a specified range.
     * Validates input and prompts again if invalid.
     *
     * @param message The prompt message to display
     * @param min The minimum acceptable value (inclusive)
     * @param max The maximum acceptable value (inclusive)
     * @return The validated integer input from the user
     */
    public int askForInt(String message, int min, int max) {
        showLog(message);
        int answer = 0;

        answer = intVerification(answer);

        while (answer < min || answer > max){
            showLog("Please enter a number between " + min + " and " + max+".");
            answer = intVerification(answer);
        }

        return answer;
    }

    /**
     * Verifies that the user input is a valid integer.
     * Handles InputMismatchException and prompts for valid input.
     *
     * @param answer The initial answer value (typically 0)
     * @return A valid integer from user input
     */
    private int intVerification(int answer) {
        boolean exit = false;

        while(!exit) {
            try {
                answer = userInteraction.getUserInt();
                exit = true;
            } catch (InputMismatchException e) {
                userInteraction.clearBuffer();
                showLog("Integer expected, try again :");
            }
        }
        return answer;
    }

    /**
     * Displays a log message to the user.
     * Delegates to the view component for output.
     *
     * @param message The message to display
     */
    public void showLog(String message) {
        view.displayLog(message);
    }
}