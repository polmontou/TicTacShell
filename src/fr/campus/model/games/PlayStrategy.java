package fr.campus.model.games;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.model.player.Player;

/**
 * Strategy interface defining the core contract for all game types.
 * Implements the Strategy pattern to allow different game implementations
 * while maintaining a common interface for game initialization, state checking,
 * and property access.
 */
public interface PlayStrategy {

    /**
     * Initializes the game with the given players.
     * Sets up the game board and prepares the game state for play.
     *
     * @param players The array of players participating in the game
     */
    public void init(Player[] players);

    /**
     * Checks whether the game has ended.
     * Evaluates win conditions and tie scenarios based on the current game state.
     *
     * @param moveCount The total number of moves played so far
     * @return The current game status: WIN if a player has won,
     *         TIE if the board is full with no winner,
     *         or NOTHING if the game is still ongoing
     */
    public RoundEnd isOver(int moveCount);

    /**
     * Gets the name of the game type.
     *
     * @return The game name as a string
     */
    public String getName();

    /**
     * Gets the game board.
     *
     * @return The Board instance representing the current game state
     */
    public Board getBoard();

    /**
     * Gets the players participating in the game.
     *
     * @return An array containing all players in the game
     */
    public Player[] getPlayers();
}