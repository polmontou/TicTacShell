package fr.campus.game.games;

import fr.campus.game.Game;

public class GameFactory {

    public static Game createGame(Games game)
    {
        GameType gt = switch (game) {
            case TICTACTOE -> new TicTacToe();
            case PUISSANCE4 -> new Puissance4();
            case GOMOKU -> new Gomoku();
            case FREESTYLE -> new Freestyle();
        };
        return new Game(gt);
    }
}
