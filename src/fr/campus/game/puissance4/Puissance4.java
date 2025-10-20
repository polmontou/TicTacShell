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
int test =0;
        do {
            View.displayBoard(board, line,column);
            test++;
        }while ((test == 1));
    }

    @Override
    public void init() {
        super.init();
        board = new Board();

        winRule = 4;
    }

}
