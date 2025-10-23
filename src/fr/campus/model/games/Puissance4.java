package fr.campus.model.games;

import fr.campus.view.View;
import fr.campus.model.player.Player;

public class Puissance4 extends GameType {

    Puissance4() {
        super("Puissance4",4,6,7);
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
