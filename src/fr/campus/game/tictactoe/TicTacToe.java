package fr.campus.game.tictactoe;


import fr.campus.game.GameType;
import fr.campus.support.Cell;
import fr.campus.support.Player;
import fr.campus.support.Tool;

public class TicTacToe extends GameType {
    private Player[] players;
    private final static int BOARD_SIZE = 9;
    private final static int WIN_RULE = 6;
    private Cell[][] board;
    private final static int PLAYER_LIMIT = 2;

    public TicTacToe(int numberOfPlayers) {
        super("TicTacToe");
        board = new Cell[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Cell();
            }
        }

        this.players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers && i < PLAYER_LIMIT; i++) {
            players[i] = new Player("Player " + (i + 1), TicTacToePawn.distributePawn(i).getRepresentation());
        }
    };

    public void displayBoard() {
        System.out.print("  |");
        for (int l = 0; l < BOARD_SIZE; l++) {
            System.out.print(" "+ (l+1) +" |");
        }
        System.out.println();
        System.out.print("---");
        for (int k = 0; k < BOARD_SIZE; k++) {
            System.out.print("----");
        }
        System.out.println();
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i+1 +" |");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j].toString()+"|");
            }
            System.out.println();
            System.out.print("---");
            for (int k = 0; k < BOARD_SIZE; k++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

    public void play() {
        int moveCount = 0;
        Player lastPlayer = null;
        TicTacToeRoundEnd results = null;

        do {
            displayBoard();

            int currentIndex = moveCount%2;
            getMove(players[currentIndex]);
            lastPlayer = players[currentIndex];

            moveCount++;

            results = isOver(moveCount);
        } while (results == TicTacToeRoundEnd.NOTHING);

        displayBoard();

        if (results.isWon()) {
            Tool.message(lastPlayer.getName() + " wins the game!");
        } else {
            Tool.message("It's a tie!");
        }
    }

    private TicTacToeRoundEnd isOver(int moveCount) {
        boolean win = false;
        boolean over = false;

        if (isWon()) {
            return TicTacToeRoundEnd.WIN;
        } else if (boardIsFull(moveCount)) {
            return TicTacToeRoundEnd.TIE;
        } else {
            return TicTacToeRoundEnd.NOTHING;
        }


    }
    private boolean boardIsFull(int moveCount) {
        return moveCount == BOARD_SIZE * BOARD_SIZE;
    }

    private boolean isWon() {
        for (int line = 0; line < BOARD_SIZE; line++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (!board[line][col].isEmpty()) {
                    if (isInWinLine(line, col) || isInWinCol(line, col) || isInWinDiag(line, col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isInWinDiag(int line, int col) {
        int sameCellsInRow = 0;
        if ((line + WIN_RULE >=  BOARD_SIZE || line - WIN_RULE < 0 ) && (col + WIN_RULE >= BOARD_SIZE)) return false;

        while (line + 1 < BOARD_SIZE && col + 1 < BOARD_SIZE && board[line][col].getPlayer() == board[line + 1][col + 1].getPlayer()) {
            sameCellsInRow++;
            line++;
            col++;
            if (sameCellsInRow == WIN_RULE - 1) {
                return true;
            }
        }

        while (line - 1 >= 0 && col + 1 < BOARD_SIZE && board[line][col].getPlayer() == board[line - 1][col + 1].getPlayer()) {
            sameCellsInRow++;
            line--;
            col++;
            if (sameCellsInRow == WIN_RULE - 1) {
                return true;
            }
        }
        return false;
    }

    private boolean isInWinCol(int line, int col) {
        if (line + WIN_RULE >= BOARD_SIZE) return false;

        int sameCellsInRow = 0;
        while (line + 1 < BOARD_SIZE && board[line][col].getPlayer() == board[line+1][col].getPlayer()) {
            sameCellsInRow++;
            line++;
            if (sameCellsInRow == WIN_RULE - 1) {
                return true;
            }
        }
        return false;
    }

    private boolean isInWinLine(int line, int col) {
        if (col + WIN_RULE >= BOARD_SIZE) return false;

        int sameCellsInRow = 0;
        while (col + 1 < BOARD_SIZE && board[line][col].getPlayer() == board[line][col + 1].getPlayer()) {
            sameCellsInRow++;
            col++;
            if (sameCellsInRow == WIN_RULE - 1) {
                return true;
            }
        }
        return false;
    }

    private void getMove(Player player) {
        int row;
        int col;
        Tool.message("It's "+player.getName()+"'s turn!");
        do {
            Tool.message("Choose a row between 1 and 3 (integer expected) : ");
            row = Tool.getUserInt();
            Tool.message("Choose a column between 1 and 3 (integer expected) : ");
            col = Tool.getUserInt();
        } while (!checkMove(row, col));
        updateCell(row, col, player);
    }

    private void updateCell(int row, int col, Player player) {
        board[row-1][col-1].setPlayer(player);
    }

    private boolean checkMove(int row, int column) {
        if (checkRange(row, column)) {
            if (checkCellAvailability(row, column)) {
                return true;
            }
            Tool.message("Cell not empty, try again!");
        } else {
            Tool.message("You're out of bounds!");
        }
        return false;
    }

    private boolean checkCellAvailability(int row, int col) {
        return board[row-1][col-1].isEmpty();
    }

    private boolean checkRange(int row, int column) {
        if (row-1 < 0 || row-1 >= BOARD_SIZE || column-1 < 0 || column-1 >= BOARD_SIZE) {
            return false;
        }
        return true;
    }
}
