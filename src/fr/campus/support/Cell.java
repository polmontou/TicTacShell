package fr.campus.support;

import fr.campus.support.player.Player;

public class Cell {
    private String content;
    private Player player;
    private boolean empty;

    public Cell() {
        this.content = "   ";
        empty = true;
    }

    //Setter
    public void setPlayer(Player player) {
        this.player = player;
        empty = false;
        setContent(player.getPawn());
    }
    public void setContent(String content) {
        this.content = " "+content+" ";
    }
    //Getter
    public String toString() {
        return content;
    }
    public Player getPlayer() {return player;}
    public boolean isEmpty() {return empty;}

}
