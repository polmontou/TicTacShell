package fr.campus.game.tictactoe;

public enum TicTacToeRoundEnd {
    NOTHING(false, false),
    TIE(true, false),
    WIN(true, true);

    private boolean over;
    private boolean win;

    TicTacToeRoundEnd(boolean over, boolean win) {
        this.over = over;
        this.win = win;
    }

    public boolean isOver() {
        return over;
    }
    public boolean isWon() {
        return win;
    }
}
