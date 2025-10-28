package fr.campus.model.games;

/**
 * Represents the Tic Tac Toe game.
 * Played on a 3x3 board where players must align 3 pawns to win.
 * Also known as noughts and crosses.
 */
public class TicTacToe extends GameType {
    /**
     * TicTacToe game constructor.
     *
     * @param name The game name
     * @param winRule The number of aligned cells needed to win (3)
     * @param lineMax The number of rows (3)
     * @param columnMax The number of columns (3)
     */
    TicTacToe(String name, int winRule, int lineMax, int columnMax) {
        super(name, winRule, lineMax, columnMax);
    }
}