package fr.campus.model.games;

public class GameFactory {

    public static GameType createGame(Games game, String name, int winRule, int lineMax, int columnMax)
    {
        return switch (game) {
            case TICTACTOE -> new TicTacToe(name,  winRule, lineMax, columnMax);
            case PUISSANCE4 -> new Puissance4(name,  winRule, lineMax, columnMax);
            case GOMOKU -> new Gomoku(name,  winRule, lineMax, columnMax);
            case FREESTYLE -> new Freestyle(name,  winRule, lineMax, columnMax);
        };
    }
}
