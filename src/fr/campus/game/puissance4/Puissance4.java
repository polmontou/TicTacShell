package fr.campus.game.puissance4;

import fr.campus.game.GameType;
import fr.campus.game.RoundEnd;
import fr.campus.game.board.Board;
import fr.campus.main.Menu;
import fr.campus.support.UserInteraction;
import fr.campus.support.View;
import fr.campus.support.player.Player;

import javax.swing.border.Border;

public class Puissance4 extends GameType {


    private final int line = 6;
    private final int column = 7;

    public Puissance4() {
        super("Puissance4");
    }


    @Override
    public void play() {
        int moveCount = 0;
        Player lastPlayer = null;
        RoundEnd result = null;
        do {

            View.displayBoard(board, line, column);

            int currentIndex = moveCount%2;

            getMove(players[currentIndex]);

            lastPlayer = players[currentIndex];

            moveCount++;

            result = isOver(moveCount);



        } while (result == RoundEnd.NOTHING);


    }


    private void getMove(Player player)
    {
        int col;
        View.message(player.getName() + "'s turn !\n");
        do{
            col = player.chooseInt("Choose a column between 1 and "+ column +" (integer expected) : ",1, column);
        }while (!checkMove(col));

        board.updateCell(col,player);
    }


    private RoundEnd isOver (int moveCount)
    {
        if(isWon())
        {
            return RoundEnd.WIN;
        }
        else if(board.isFull(moveCount,column,line))
        {
            return RoundEnd.TIE;
        }

        return RoundEnd.NOTHING;
    }

    private boolean isWon() {
        for (int line = 0; line < board.getBoardSize(); line++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
                if (!board.getCell(line,col).isEmpty()) {
                    if (isInWinLine(line, col) || isInWinCol(line, col) || isInWinDiag(line, col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isInWinDiag(int line, int col) {
        return checkDiag(line, col, 1) || checkDiag(line, col, -1);
    }


    private boolean isInWinCol(int line, int col) {
        if (line + winRule >= board.getBoardSize()) return false;

        int sameCellsInRow = 1;
        while (line + 1 < board.getBoardSize() && board.getCell(line,col).getPlayer() == board.getCell(line+1,col).getPlayer()) {
            sameCellsInRow++;
            line++;
            if (sameCellsInRow == winRule) {
                return true;
            }
        }
        return false;
    }


    private boolean checkDiag(int line, int col, int gapLine) {
        int sameCellsInRow = 1;
        if ((gapLine > 0 && line + winRule > board.getBoardSize()) || (gapLine < 0 && (line - winRule + 1)< 0) || (col + winRule > board.getBoardSize())) return false;

        if (gapLine > 0) {
            int testLine = line;
            int testCol = col;
            while (testLine + gapLine < board.getBoardSize() && testCol + 1 < board.getBoardSize() && board.getCell(testLine,testCol).getPlayer() == board.getCell(testLine + gapLine,testCol + 1).getPlayer()) {
                sameCellsInRow++;
                testLine++;
                testCol++;
                if (sameCellsInRow == winRule) {
                    return true;
                }
            }
        } else {
            while (line - 1 >= 0 && col + 1 < board.getBoardSize() && board.getCell(line,col).getPlayer() == board.getCell(line - 1,col + 1).getPlayer()) {
                sameCellsInRow++;
                line--;
                col++;
                if (sameCellsInRow == winRule) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isInWinLine(int line, int col) {
        if (col + winRule >= board.getBoardSize()) return false;

        int sameCellsInRow = 1;
        while (col + 1 < board.getBoardSize() && board.getCell(line,col).getPlayer() == board.getCell(line,col + 1).getPlayer()) {
            sameCellsInRow++;
            col++;
            if (sameCellsInRow == winRule) {
                return true;
            }
        }
        return false;
    }


    private boolean checkMove(int col)
    {
        if(checkRange(col))
        {
            if(checkCellAvailability(col))
            {
                return true;
            }
            View.message("Column already full, try again\n");
        }
        else
        {
            View.message("You're out of bounds\n");
        }


        return false;
    }

    private boolean checkRange(int col)
    {
        return col >= 1 && col <= column;
    }

    private boolean checkCellAvailability(int col)
    {
        for(int i = 0; i < line; i++)
        {
            if(board.getCell(i,col-1).isEmpty())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init() {
        super.init();
        board = new Board();

        winRule = 4;
    }

}
