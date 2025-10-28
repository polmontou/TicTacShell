package fr.campus.model.board;

import fr.campus.model.player.Player;

/**
 * Represents a single cell on the game board.
 * Each cell can be empty or occupied by a player.
 */
public class Cell {
    private String content;
    private Player player;
    private boolean empty;

    /**
     * Cell constructor.
     * Initializes an empty cell with default content.
     */
    public Cell() {
        this.content = "   ";
        empty = true;
    }

    /**
     * Sets the player for this cell and updates its content.
     * Marks the cell as non-empty.
     *
     * @param player The player occupying the cell
     */
    public void setPlayer(Player player) {
        this.player = player;
        empty = false;
        setContent(player.getPawn());
    }

    /**
     * Sets the visual content of the cell.
     *
     * @param content The pawn representation to display
     */
    private void setContent(String content) {
        this.content = " "+content+" ";
    }

    /**
     * Returns the string representation of the cell for display.
     *
     * @return The cell content
     */
    public String toString() {
        return content;
    }

    /**
     * Gets the player occupying this cell.
     *
     * @return The player, or null if empty
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Checks if the cell is empty.
     *
     * @return true if empty, false if occupied
     */
    public boolean isEmpty() {
        return empty;
    }
}