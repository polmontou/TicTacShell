package fr.campus.support;

public class Cell {
    private String content;
    private Player player;

    public Cell() {
        this.content = "   ";
    }

    //Setter
    public void setPlayer(Player player) {
        this.player = player;
        setContent(player.getPawn());
    }
    public void setContent(String content) {
        this.content = " "+content+" ";
    }
    //Getter
    public String toString() {
        return content;
    }

}
