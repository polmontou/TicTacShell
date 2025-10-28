package fr.campus.model.games;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.model.player.Player;

/**
 * Represents a freestyle game with customizable rules.
 * Players can define board size and win conditions.
 */
public class Freestyle extends GameType {

    /**
     * Freestyle game constructor.
     *
     * @param name The game name
     * @param winRule The number of aligned cells needed to win
     * @param lineMax The number of rows
     * @param columnMax The number of columns
     */
    Freestyle(String name, int winRule, int lineMax, int columnMax) {
        super(name, winRule, lineMax, columnMax);
    }

    /**
     * Initializes the freestyle game with players and board.
     *
     * @param players The array of players
     */
    @Override
    public void init(Player[] players) {
        this.status = RoundEnd.NOTHING;
        this.players = players;
        this.board = new Board(lineMax,columnMax);
    }
}