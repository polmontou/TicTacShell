package fr.campus.view;

import fr.campus.model.board.Board;

/**
 * View class responsible for displaying information to the user.
 * Handles all console output operations including board rendering and messages.
 */
public class View {

    /**
     * Displays a log message to the console.
     *
     * @param message The message to display
     */
    public void displayLog(String message){
        System.out.println(message);
    }

    /**
     * Displays the game board to the console.
     * Renders the board with row and column numbers, grid lines, and cell contents.
     * Format includes:
     * - Column numbers at the top
     * - Row numbers on the left
     * - Grid separators between cells
     * - Cell contents (pawns or empty spaces)
     *
     * @param board The game board to display
     */
    public void displayBoard(Board board) {
        // Display column numbers header
        System.out.print("  |");
        for (int l = 0; l < board.getBoardSizeX(); l++) {
            System.out.print(" " + (l + 1) + " |");
        }
        System.out.println();

        // Display top border
        System.out.print("---");
        for (int k = 0; k < board.getBoardSizeX(); k++) {
            System.out.print("----");
        }
        System.out.println();

        // Display board rows with content
        for (int i = 0; i < board.getBoardSizeY(); i++) {
            // Display row number
            System.out.print(i + 1 + " |");

            // Display cell contents
            for (int j = 0; j < board.getBoardSizeX(); j++) {
                System.out.print(board.getCell(i, j).toString() + "|");
            }
            System.out.println();

            // Display row separator
            System.out.print("---");
            for (int k = 0; k < board.getBoardSizeX(); k++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }
}