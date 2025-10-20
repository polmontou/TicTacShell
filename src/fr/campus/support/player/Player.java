package fr.campus.support.player;

public abstract class Player {
    private String name;
    private String pawn;

    public Player(String name, String pawn) {
        this.name = name;
        this.pawn = pawn;
    }

    public abstract int chooseInt(String message, int minValue, int maxValue);
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
