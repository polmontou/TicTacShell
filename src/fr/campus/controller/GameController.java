package fr.campus.controller;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Pawn;
import fr.campus.model.games.GameType;
import fr.campus.model.games.Games;
import fr.campus.model.player.BotPlayer;
import fr.campus.model.player.HumanPlayer;
import fr.campus.model.player.Player;
import fr.campus.view.View;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameController {
    private final static int PLAYER_LIMIT = 2;
    private View view;
    private GameType currentGame;

    public GameController() {
        this.view = new View();
    }

    public void initGame(){
        int gameChoice = view.displayMenu("game");
        this.currentGame = parseUserChoice(gameChoice, Games.class).createGame(this);

        int playerChoice = view.displayMenu("player");
        Player[] players = createPlayerSet(playerChoice);
        currentGame.init(players);
    }

    public void play() {
        int moveCount = 0;
        Player lastPlayer = null;
        RoundEnd results = null;
        Player[] players = currentGame.getPlayers();

        do {
            view.displayBoard(currentGame.getBoard());

            int currentIndex = moveCount%2;
            currentGame.getMove(players[currentIndex]);
            lastPlayer = players[currentIndex];

            moveCount++;

            results = currentGame.isOver(moveCount);
        } while (results == RoundEnd.NOTHING);

        view.displayBoard(currentGame.getBoard());

        if (results.isWon()) {
            View.message(lastPlayer.getName() + " wins the game!");
        } else {
            View.message("It's a tie!");
        }
    }

    private Player[] createPlayerSet(int choice) {
        return parseUserPlayerChoice(choice);
    }

    public static <E extends Enum<E>> E parseUserChoice(int choice, Class<E> enumClass) {
        choice--;
        E[] options = enumClass.getEnumConstants();

        return options[choice];
    }

    public Player[] parseUserPlayerChoice(int choice) {
        Player[] players = new Player[PLAYER_LIMIT];
        switch (choice) {
            case 1:
                for (int i = 0; i < players.length; i++) {
                    players[i] = new HumanPlayer("Player "+ (i+1), Pawn.distributePawn(i).getRepresentation(), this);
                }
                break;
            case 2:
                int j = 0;
                players[j] = new HumanPlayer("Player "+ (j+1), Pawn.distributePawn(j).getRepresentation(), this);
                players[j+1] = new BotPlayer("Bot "+j,  Pawn.distributePawn(j+1).getRepresentation(), this);
                break;
            case 3:
                for (int i = 0; i < players.length; i++) {
                    players[i] = new BotPlayer("Bot "+ (i+1), Pawn.distributePawn(i).getRepresentation(), this);
                }
        }
        List<Player> shufflableList= Arrays.asList(players);
        Collections.shuffle(shufflableList);
        shufflableList.toArray(players);

        return players;
    }

    public int askForInt(String message, int min, int max) {
        return view.askForInt(message, min, max);
    }
}
