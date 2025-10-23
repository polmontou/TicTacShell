package fr.campus.controller;

import fr.campus.model.board.Pawn;
import fr.campus.model.games.GameType;
import fr.campus.model.player.BotPlayer;
import fr.campus.model.player.HumanPlayer;
import fr.campus.model.player.Player;
import fr.campus.view.View;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameController {
    private View view;
    private GameType currentGame;

    public GameController() {
        this.view = new View();
    }

    public void initGame(){
        view.displayMenu("game");
    }

    public void play() {
        currentGame.init();
        currentGame.play();
    }

    public static <E extends Enum<E>> E parseUserChoice(int choice, Class<E> enumClass) {
        choice--;
        E[] options = enumClass.getEnumConstants();

        return options[choice];
    }

    public static Player[] parseUserPlayerChoice(int choice) {
        Player[] players = new Player[2];
        switch (choice) {
            case 1:
                for (int i = 0; i < players.length; i++) {
                    players[i] = new HumanPlayer("Player "+ (i+1), Pawn.distributePawn(i).getRepresentation());
                }
                break;
            case 2:
                int j = 0;
                players[j] = new HumanPlayer("Player "+ (j+1), Pawn.distributePawn(j).getRepresentation());
                players[j+1] = new BotPlayer("Bot "+j,  Pawn.distributePawn(j+1).getRepresentation());
                break;
            case 3:
                for (int i = 0; i < players.length; i++) {
                    players[i] = new BotPlayer("Bot "+ (i+1), Pawn.distributePawn(i).getRepresentation());
                }
        }
        List<Player> shufflableList= Arrays.asList(players);
        Collections.shuffle(shufflableList);
        shufflableList.toArray(players);

        return players;
    }
}
