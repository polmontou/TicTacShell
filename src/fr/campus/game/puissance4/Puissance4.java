package fr.campus.game.puissance4;

import fr.campus.game.GameType;
import fr.campus.game.board.Board;
import fr.campus.main.Menu;
import fr.campus.support.UserInteraction;
import fr.campus.support.player.Player;

import javax.swing.border.Border;

public class Puissance4 extends GameType {







    public Puissance4() {
        super("Puissance4");
    }


    @Override
    public void play() {

    }

    @Override
    public void init() {
        super.init();
        board = new Board();

        winRule = 4;
    }

}
