package fr.campus.controller;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.model.board.Pawn;
import fr.campus.model.games.GameType;
import fr.campus.model.games.Games;
import fr.campus.model.games.Puissance4;
import fr.campus.model.player.BotPlayer;
import fr.campus.model.player.HumanPlayer;
import fr.campus.model.player.Player;
import fr.campus.view.Menu;
import fr.campus.view.View;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameController {
    private final static int PLAYER_LIMIT = 2;
    private GameType currentGame;
    private Menu menu;
    public GameController() {
        this.view = new View();
    }

    public void initGame(){
        int gameChoice = view.displayMenu("game");
        Games wantedGame = parseUserChoice(gameChoice, Games.class);

        if (wantedGame == Games.FREESTYLE) {
            int winRule =  view.askForInt("How many cells in a row to win the game?", 1, wantedGame.getMaxSize());
            wantedGame.setWinRule(winRule);
            int lineMax = view.askForInt("How many rows in your game? (choose between 1 and " + wantedGame.getMaxSize() + "): ", 1, wantedGame.getMaxSize());
            wantedGame.setLineMax(lineMax);
            int columnMax =  view.askForInt("How many columns in your game? (choose between 1 and " + wantedGame.getMaxSize() + "): ", 1, wantedGame.getMaxSize());
            wantedGame.setColumnMax(columnMax);
        }
        currentGame = wantedGame.createGame(wantedGame.getName(), wantedGame.getWinRule(), wantedGame.getLineMax(), wantedGame.getColumnMax());


        int playerChoice = view.displayMenu("player");
        Player[] players = createPlayerSet(playerChoice);
        currentGame.init(players);
    }


    public void play() {
        int moveCount = 0;
        Player lastPlayer = null;
        RoundEnd results = null;
        Player[] players = currentGame.getPlayers();
        Board board = currentGame.getBoard();
        int lineMax = board.getBoardSizeY();
        int columnMax = board.getBoardSizeX();

        do {
            int currentIndex = moveCount%2;
            int col;
            int line;
            Player currentPlayer = currentGame.getPlayers()[currentIndex];
            boolean wrongRange;
            boolean freeCell;

            view.displayBoard(board);

            if (currentGame instanceof Puissance4) {
                do {
                    col = getMove(currentPlayer, "Choose a column between 1 and " + columnMax + " (integer expected) : ", 1, columnMax);

                    freeCell = board.checkColumnAvailability(col);
                    if (!freeCell) {
                        View.message("Column already full, try again!\n");//Refacto
                    }

                } while (!freeCell);
                board.updateCell(col, currentPlayer);
            } else {
                do {
                    line = getMove(currentPlayer, "Choose a line between 1 and " + lineMax + " (integer expected) : ", 1, lineMax);
                    col = getMove(currentPlayer, "Choose a column between 1 and " + columnMax + " (integer expected) : ", 1, columnMax);

                    freeCell = board.checkCellAvailability(line, col);
                    if (!freeCell) {
                        View.message("Cell not empty, try again!\n"); //Refacto
                    }

                } while (!freeCell);
                board.updateCell(line, col, currentPlayer);
            }

            lastPlayer = currentPlayer;

            moveCount++;

            results = currentGame.isOver(moveCount);
        } while (results == RoundEnd.NOTHING);

        view.displayBoard(currentGame.getBoard());

        if (results.isWon()) {
            View.message(lastPlayer.getName() + " wins the game!");//Refacto
        } else {
            View.message("It's a tie!");//Refacto
        }
    }


    private int getMove(Player player, String message, int minValue, int maxValue) {
        int choice;

        if (player instanceof BotPlayer) {
            choice = player.chooseInt(maxValue);
            View.message(message + "\n"+player.getName()+" chooses "+choice);
        } else {
            choice = view.askForInt(message, minValue, maxValue);
        }
        return choice;
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
