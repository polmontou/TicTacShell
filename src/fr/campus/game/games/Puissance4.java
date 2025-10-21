package fr.campus.game.games;

import fr.campus.support.View;
import fr.campus.support.player.Player;

public class Puissance4 extends GameType {

    public Puissance4() {
        super("Puissance4",4,6,7);
    }

    protected void getMove(Player player)
    {
        int col;
        View.message(player.getName() + "'s turn !\n");
        do{
            col = player.chooseInt("Choose a column between 1 and "+ COLUMN_MAX +" (integer expected) : ",1, COLUMN_MAX);
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
        return col >= 1 && col <= COLUMN_MAX;
    }

    private boolean checkCellAvailability(int col)
    {
        for(int i = 0; i < LINE_MAX; i++)
        {
            if(board.getCell(i,col-1).isEmpty())
            {
                return true;
            }
        }
        return false;
    }



}
