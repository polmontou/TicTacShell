package fr.campus.support;

public class Player {
    private String name;
    private String pawn;

    public Player(String name, String pawn) {
        this.name = name;
        this.pawn = pawn;
    }

    //Setter
    public void setPawn(String pawn) {
        this.pawn = pawn;
    }

    //Getter
    public String getName() {
        return name;
    }
    public String getPawn() {
        return pawn;
    }
}
