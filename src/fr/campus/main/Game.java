package fr.campus.main;

import fr.campus.game.GameType;
import fr.campus.support.Player;

public class Game {
    private GameType currentGame;

    public Game(GameType game) {
        this.currentGame = game;
    }

    public void play() {
        currentGame.play();
    }

}
