package fr.campus.game;

import fr.campus.game.games.GameType;

public class Game {
    private GameType currentGame;

    public Game(GameType game) {
        this.currentGame = game;
    }

    public void play() {
        currentGame.init();
        currentGame.play();
    }

}
