package fr.campus.model.games;

/**
 * Factory class for creating game instances.
 * Implements the Factory design pattern for game creation.
 */
public class GameFactory {

    /**
     * Creates a game instance based on the specified game type.
     *
     * @param game The game enumeration type
     * @param name The game name
     * @param winRule The number of aligned cells needed to win
     * @param lineMax The number of rows
     * @param columnMax The number of columns
     * @return A new game instance of the appropriate type
     */
    public static PlayStrategy createGame(GamesPreset game, String name, int winRule, int lineMax, int columnMax) {
        return switch (game) {
            case TICTACTOE_PRESET -> new TicTacToe(name,  winRule, lineMax, columnMax);
            case PUISSANCE4_PRESET -> new Puissance4(name,  winRule, lineMax, columnMax);
            case GOMOKU_PRESET -> new Gomoku(name,  winRule, lineMax, columnMax);
            case FREESTYLE_PRESET -> new Freestyle(name,  winRule, lineMax, columnMax);
        };
    }
}