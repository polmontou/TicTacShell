package fr.campus.game;

public enum RoundEnd {
    NOTHING( false),
    TIE(false),
    WIN( true);

    private boolean win;

    RoundEnd(boolean win) {
        this.win = win;
    }

    public boolean isWon() {
        return win;
    }
}
