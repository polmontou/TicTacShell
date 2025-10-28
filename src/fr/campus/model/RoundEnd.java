package fr.campus.model;

/**
 * Enumeration representing the possible end states of a game round.
 * Used to determine if the game is still in progress, won, or tied.
 */
public enum RoundEnd {
    NOTHING,
    WIN,
    TIE;

    public boolean isWon() {
        return this == WIN;
    }
}