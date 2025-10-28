package fr.campus.model.games;

/**
 * Represents the Connect 4 game (Puissance 4 in French).
 * Played on a 6x7 board with gravity-based piece placement.
 * Players must align 4 pawns vertically, horizontally, or diagonally to win.
 */
public class Puissance4 extends GameType {
    /**
     * Puissance4 game constructor.
     *
     * @param name The game name
     * @param winRule The number of aligned cells needed to win (4)
     * @param lineMax The number of rows (6)
     * @param columnMax The number of columns (7)
     */
    Puissance4(String name, int winRule, int lineMax, int columnMax) {
        super(name, winRule, lineMax, columnMax);
    }
}