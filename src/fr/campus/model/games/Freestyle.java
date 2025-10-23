package fr.campus.model.games;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.model.player.Player;

public class Freestyle extends GameType{

    Freestyle(String name, int winRule, int lineMax, int columnMax) {
        super(name, winRule, lineMax, columnMax);
    };

    @Override
    public void init(Player[] players)
    {
        this.status = RoundEnd.NOTHING;
        this.players = players;
        this.board = new Board(lineMax,columnMax);
    }
}
