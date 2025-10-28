package fr.campus.model.games;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.model.player.Player;

/**
 * Abstract base class for all game types.
 * Defines common properties and behaviors for different games.
 */
public abstract class GameType implements PlayStrategy {
    protected String name;
    protected Player[] players;
    protected Board board;
    protected int winRule;
    protected int lineMax;
    protected int columnMax;
    protected RoundEnd status;

    /**
     * GameType constructor.
     *
     * @param name The game name
     * @param winRule The number of aligned cells needed to win
     * @param lineMax The number of rows
     * @param columnMax The number of columns
     */
    public GameType(String name, int winRule, int lineMax, int columnMax) {
        this.name = name;
        this.winRule = winRule;
        this.lineMax = lineMax;
        this.columnMax = columnMax;
    }

    /**
     * Initializes the game with players and creates the board.
     *
     * @param players The array of players
     */
    public void init(Player[] players) {
        this.players = players;
        board = new Board(lineMax, columnMax);
        this.status = RoundEnd.NOTHING;
    }

    /**
     * Checks if the game is over.
     * Tests for win or tie conditions.
     *
     * @param moveCount The total number of moves made
     * @return The game status (WIN, TIE, or NOTHING)
     */
    public RoundEnd isOver (int moveCount) {
        if(board.isWon(winRule)) {
            status = RoundEnd.WIN;
            return status;
        }
        else if(board.isFull(moveCount)) {
            status = RoundEnd.TIE;
            return status;
        }
        return status;
    }

    /**
     * Gets the game name.
     *
     * @return The game name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the game board.
     *
     * @return The board instance
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the players in the game.
     *
     * @return The array of players
     */
    public Player[] getPlayers() {
        return players;
    }
}