package fr.campus.view;

import java.util.*;

/**
 * Handles direct user input operations.
 * Manages the scanner for reading user input from the console.
 */
public class UserInteraction {
    private Scanner sc = new Scanner(System.in);

    /**
     * Gets an integer input from the user.
     *
     * @return The integer entered by the user
     * @throws java.util.InputMismatchException if the input is not a valid integer
     */
    public int getUserInt() {
        return sc.nextInt();
    }

    /**
     * Gets a string input from the user.
     * Reads a full line of text.
     *
     * @return The string entered by the user
     */
    public String getUserString() {
        return sc.nextLine();
    }

    /**
     * Clears the scanner buffer.
     * Used to consume remaining input after reading an integer,
     * preventing input mismatch issues.
     */
    public void clearBuffer() {
        sc.nextLine();
    }
}