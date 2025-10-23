package fr.campus.model.games;

import fr.campus.controller.GameController;

public class GameFactory {

    public static GameType createGame(Games game, GameController controller)
    {
        return switch (game) {
            case TICTACTOE -> new TicTacToe(controller);
            case PUISSANCE4 -> new Puissance4(controller);
            case GOMOKU -> new Gomoku(controller);
            case FREESTYLE -> new Freestyle(controller);
        };
    }
}
