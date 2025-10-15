package fr.campus.main;

import fr.campus.game.GameType;
import fr.campus.support.Player;

public class Game {
    private Player player1;
    private Player player2;
    private GameType currentGame;

    public Game(GameType game) {
        this.player1 = new Player("toto");
        this.currentGame = game;
    }

    public void play() {
        currentGame.play(player1);
    }

}
