package fr.campus.model.games;

/**
 * Represents the Gomoku game (Five in a Row).
 * Played on a 15x15 board, players must align 5 pawns to win.
 */
public class Gomoku extends GameType {
    /**
     * Gomoku game constructor.
     *
     * @param name The game name
     * @param winRule The number of aligned cells needed to win (5)
     * @param lineMax The number of rows (15)
     * @param columnMax The number of columns (15)
     */
    Gomoku(String name, int winRule, int lineMax, int columnMax) {
        super(name, winRule, lineMax, columnMax);
    }
}