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

    protected void getMove(Player player)
    {
        int col;
        View.message(player.getName() + "'s turn !\n");
        do{
            col = player.chooseInt("Choose a column between 1 and "+ columnMax +" (integer expected) : ",1, columnMax);
        } while (!checkMove(col));

        board.updateCell(col,player);
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
