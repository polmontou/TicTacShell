package fr.campus.model.games;

import fr.campus.model.RoundEnd;
import fr.campus.model.board.Board;
import fr.campus.view.View;
import fr.campus.model.player.Player;

public abstract class GameType {
    protected String name;
    protected Player[] players;
    protected Board board;
    protected  int winRule;
    protected  int lineMax;
    protected  int columnMax;
    protected RoundEnd status;

    public GameType(String name, int winRule, int lineMax, int columnMax) {
        this.name = name;
        this.winRule = winRule;
        this.lineMax = lineMax;
        this.columnMax = columnMax;
    }

    public void init(Player[] players) {
        this.players = players;
        board = new Board(lineMax, columnMax);
        this.status = RoundEnd.NOTHING;
    }

    public RoundEnd isOver (int moveCount)
    {
        if(board.isWon(winRule))
        {
            status = RoundEnd.WIN;
            return status;
        }
        else if(board.isFull(moveCount))
        {
            status = RoundEnd.TIE;
            return status;
        }
        return status;
    }

    //Getters
    public String getName() {
        return name;
    }
    public Board getBoard() {
        return board;
    }
    public Player[] getPlayers() {
        return players;
    }
}
