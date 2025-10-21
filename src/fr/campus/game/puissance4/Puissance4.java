package fr.campus.game.puissance4;

import fr.campus.game.GameType;
import fr.campus.game.RoundEnd;
import fr.campus.game.board.Board;
import fr.campus.support.View;
import fr.campus.support.player.Player;

public class Puissance4 extends GameType {


    private final int lineMax = 6;
    private final int columnMax = 7;

    public Puissance4() {
        super("Puissance4");
    }

    @Override
    public void init() {
        super.init();
        board = new Board();

        winRule = 4;
    }

    @Override
    public void play() {
        int moveCount = 0;
        Player lastPlayer = null;
        RoundEnd result = null;
        do {

            View.displayBoard(board);

            int currentIndex = moveCount%2;

            getMove(players[currentIndex]);

            lastPlayer = players[currentIndex];

            moveCount++;

            result = isOver(moveCount);



        } while (result == RoundEnd.NOTHING);

        View.displayBoard(board);

        if (result.isWon()) {
            View.message(lastPlayer.getName() + " wins the game!");
        } else {
            View.message("It's a tie!");
        }

    }

    private void getMove(Player player)
    {
        int col;
        View.message(player.getName() + "'s turn !\n");
        do{
            col = player.chooseInt("Choose a column between 1 and "+ columnMax +" (integer expected) : ",1, columnMax);
        } while (!checkMove(col));

        board.updateCell(col,player);
    }


    private RoundEnd isOver (int moveCount)
    {
        if(board.isWon(winRule))
        {
            return RoundEnd.WIN;
        }
        else if(board.isFull(moveCount))
        {
            return RoundEnd.TIE;
        }
        return RoundEnd.NOTHING;
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
        return col >= 1 && col <= columnMax;
    }

    private boolean checkCellAvailability(int col)
    {
        for(int i = 0; i < lineMax; i++)
        {
            if(board.getCell(i,col-1).isEmpty())
            {
                return true;
            }
        }
        return false;
    }



}
