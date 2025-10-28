package fr.campus.controller;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.model.board.Pawn;
import fr.campus.model.games.*;
import fr.campus.model.player.BotPlayer;
import fr.campus.model.player.HumanPlayer;
import fr.campus.model.player.Player;
import fr.campus.view.Menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.System.exit;

/**
 * Main game controller for the application.
 * Manages the application flow, game initialization, and player interactions.
 * Handles state transitions and game loop execution.
 */
public class GameController {

    private final static int PLAYER_LIMIT = 2;
    private PlayStrategy currentGame;
    private Menu menu;
    private ControllerState state;

    /**
     * Game controller constructor.
     * Initializes the menu and sets the initial state to NEW.
     */
    public GameController() {
        this.menu = new Menu();
        this.state = ControllerState.NEW;
    }

    /**
     * Main game interaction loop.
     * Manages different controller states (NEW, INITIALIZED, PLAYING, EXIT).
     * Runs continuously until the user chooses to exit.
     */
    public void interact() {
        while (true) {
            switch (this.state) {
                case NEW:
                    initGame();
                    this.state = ControllerState.INITIALIZED;
                    break;
                case INITIALIZED:
                    this.state = ControllerState.PLAYING;
                    break;
                case PLAYING:
                    play();
                    break;
                case EXIT:
                    menu.showLog("Bye!");
                    exit(0);
            }
        }
    }

    /**
     * Initializes a new game.
     * Asks the user to choose a game type and configure players.
     * Handles the exit option if the user wants to quit.
     * For freestyle games, prompts for custom board dimensions and win rules.
     */
    private void initGame(){
        menu.displayGameChoiceMenu();
        int gameChoice = menu.askForInt("Which one do you wanna play ?",1, GamesPreset.values().length+1);
        if (gameChoice == GamesPreset.values().length+1) {
            state = ControllerState.EXIT;
            interact();
        }
        GamesPreset wantedGame = parseUserChoice(gameChoice, GamesPreset.class);

        if (wantedGame == GamesPreset.FREESTYLE_PRESET) {
            int winRule =  menu.askForInt("How many cells in a row to win the game?", 1, wantedGame.getMaxSize());
            wantedGame.setWinRule(winRule);
            int lineMax = menu.askForInt("How many rows in your game? (choose between 1 and " + wantedGame.getMaxSize() + "): ", 1, wantedGame.getMaxSize());
            wantedGame.setLineMax(lineMax);
            int columnMax =  menu.askForInt("How many columns in your game? (choose between 1 and " + wantedGame.getMaxSize() + "): ", 1, wantedGame.getMaxSize());
            wantedGame.setColumnMax(columnMax);
        }
        currentGame = GameFactory.createGame(wantedGame, wantedGame.getName(), wantedGame.getWinRule(), wantedGame.getLineMax(), wantedGame.getColumnMax());

        menu.displayPlayerChoiceMenu();
        int playerChoice = menu.askForInt("which mode do you wanna play ?",1,3);
        Player[] players = createPlayerSet(playerChoice);
        currentGame.init(players);
    }

    /**
     * Main game loop.
     * Handles turns, moves, and checks for win or tie conditions.
     * Displays the board after each move and determines the game outcome.
     * Returns to NEW state after the game ends for a new game.
     */
    private void play() {
        int moveCount = 0;
        Player lastPlayer = null;
        RoundEnd results = null;
        Player[] players = currentGame.getPlayers();
        Board board = currentGame.getBoard();
        int lineMax = board.getBoardSizeY();
        int columnMax = board.getBoardSizeX();

        do {
            int currentIndex = moveCount%2;
            Player currentPlayer = players[currentIndex];

            menu.displayBoard(board);

            playTurn(currentPlayer, columnMax, board, lineMax);

            lastPlayer = currentPlayer;

            moveCount++;

            results = currentGame.isOver(moveCount);
        } while (results == RoundEnd.NOTHING);

        menu.displayBoard(currentGame.getBoard());

        parseResults(results, lastPlayer);
        state = ControllerState.NEW;
    }

    /**
     * Executes a single turn for a player.
     * Handles input validation and board updates based on game type.
     * For Puissance4, only column selection is needed (gravity-based).
     * For other games, both row and column selection are required.
     *
     * @param currentPlayer The player taking the turn
     * @param columnMax The maximum number of columns on the board
     * @param board The game board
     * @param lineMax The maximum number of rows on the board
     */
    private void playTurn(Player currentPlayer, int columnMax, Board board, int lineMax) {
        int line;
        int col;
        boolean freeCell;

        if (currentGame instanceof Puissance4) {
            do {
                col = getMove(currentPlayer, "Choose a column between 1 and " + columnMax + " (integer expected) : ", 1, columnMax);

                freeCell = board.checkColumnAvailability(col);
                if (!freeCell) {
                    menu.showLog("Column already full, try again!\n");
                }

            } while (!freeCell);
            board.updateCell(col, currentPlayer);
        } else {
            do {
                line = getMove(currentPlayer, "Choose a line between 1 and " + lineMax + " (integer expected) : ", 1, lineMax);
                col = getMove(currentPlayer, "Choose a column between 1 and " + columnMax + " (integer expected) : ", 1, columnMax);

                freeCell = board.checkCellAvailability(line, col);
                if (!freeCell) {
                    menu.showLog("Cell not empty, try again!\n");
                }

            } while (!freeCell);
            board.updateCell(line, col, currentPlayer);
        }
    }

    /**
     * Parses and displays the game results.
     * Announces the winner if there is one, or declares a tie.
     *
     * @param results The end result of the game (WIN or TIE)
     * @param lastPlayer The last player who made a move
     */
    private void parseResults (RoundEnd results, Player lastPlayer) {
        if (results.isWon()) {
            menu.showLog(lastPlayer.getName() + " wins the game!");
        } else {
            menu.showLog("It's a tie!");
        }
    }

    /**
     * Gets a move from a player (human or bot).
     * For bot players, automatically generates and displays the choice.
     * For human players, prompts for input through the menu.
     *
     * @param player The player making the move
     * @param message The prompt message to display
     * @param minValue The minimum valid value for the move
     * @param maxValue The maximum valid value for the move
     * @return The chosen move value
     */
    private int getMove(Player player, String message, int minValue, int maxValue) {
        int choice;

        if (player instanceof BotPlayer) {
            choice = player.chooseInt(maxValue);
            menu.showLog(message + "\n"+player.getName()+" chooses "+choice);
        } else {
            choice = menu.askForInt(message, minValue, maxValue);
        }
        return choice;
    }

    /**
     * Creates a set of players based on user choice.
     * Delegates to parseUserPlayerChoice for player creation.
     *
     * @param choice The player mode choice (1: 2 humans, 2: human vs bot, 3: 2 bots)
     * @return An array of configured players
     */
    private Player[] createPlayerSet(int choice) {
        return parseUserPlayerChoice(choice);
    }

    /**
     * Parses user choice from an enumeration.
     * Converts a 1-based user input to the corresponding enum value.
     *
     * @param <E> The enumeration type
     * @param choice The user's numeric choice (1-based)
     * @param enumClass The enumeration class
     * @return The selected enumeration value
     */
    public static <E extends Enum<E>> E parseUserChoice(int choice, Class<E> enumClass) {
        choice--;
        E[] options = enumClass.getEnumConstants();

        return options[choice];
    }

    /**
     * Parses user choice for player configuration.
     * Creates and shuffles the player array based on the selected mode.
     * Mode 1: Two human players
     * Mode 2: One human player vs one bot
     * Mode 3: Two bots
     * Players are randomly shuffled to determine turn order.
     *
     * @param choice The player mode choice (1, 2, or 3)
     * @return An array of configured and shuffled players
     */
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