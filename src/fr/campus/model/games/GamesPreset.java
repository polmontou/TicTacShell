package fr.campus.model.games;

/**
 * Enumeration of available games in the application.
 * Each game has predefined or customizable parameters.
 */
public enum GamesPreset {

    TICTACTOE_PRESET("TicTacToe", 3, 3, 3),
    PUISSANCE4_PRESET("Puissance 4", 4, 6, 7),
    GOMOKU_PRESET("Gomoku", 5, 15, 15),
    FREESTYLE_PRESET("Freestyle", 100);

    private String name;
    private int maxSize;
    private int winRule;
    private int lineMax;
    private int columnMax;

    /**
     * Constructor for freestyle game with max size only.
     *
     *
     * @param name The game name
     * @param maxSize The maximum board size
     */
    GamesPreset(String name, int maxSize) {
        this.name = name;
        this.maxSize = maxSize;
    }

    /**
     * Constructor for predefined games with fixed parameters.
     *
     * @param name The game name
     * @param winRule The number of aligned cells needed to win
     * @param lineMax The number of rows
     * @param columnMax The number of columns
     */
    GamesPreset(String name, int winRule, int lineMax, int columnMax) {
        this.name = name;
        this.winRule = winRule;
        this.lineMax = lineMax;
        this.columnMax = columnMax;
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
     * Gets the win rule.
     *
     * @return The number of cells needed in a row to win
     */
    public int getWinRule() {
        return winRule;
    }

    /**
     * Gets the maximum number of rows.
     *
     * @return The number of rows
     */
    public int getLineMax() {
        return lineMax;
    }

    /**
     * Gets the maximum number of columns.
     *
     * @return The number of columns
     */
    public int getColumnMax() {
        return columnMax;
    }

    /**
     * Gets the maximum size for freestyle mode.
     *
     * @return The maximum board dimension
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Returns the string representation of the game.
     *
     * @return The game name
     */
    public String toString() {
        return this.name;
    }

    /**
     * Sets the win rule.
     *
     * @param winRule The number of cells needed in a row to win
     */
    public void setWinRule(int winRule) {
        this.winRule = winRule;
    }

    /**
     * Sets the maximum number of rows.
     *
     * @param lineMax The number of rows
     */
    public void setLineMax(int lineMax) {
        this.lineMax = lineMax;
    }

    /**
     * Sets the maximum number of columns.
     *
     * @param columnMax The number of columns
     */
    public void setColumnMax(int columnMax) {
        this.columnMax = columnMax;
    }
}