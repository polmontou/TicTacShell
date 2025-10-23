package fr.campus.model.games;

public class GameFactory {

    public static GameType createGame(Games game)
    {
        return switch (game) {
            case TICTACTOE -> new TicTacToe();
            case PUISSANCE4 -> new Puissance4();
            case GOMOKU -> new Gomoku();
            case FREESTYLE -> new Freestyle();
        };
    }
}
