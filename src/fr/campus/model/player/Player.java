package fr.campus.model.player;

public abstract class Player {
    private String name;
    private String pawn;

    public Player(String name, String pawn) {
        this.name = name;
        this.pawn = pawn;
    }

    public abstract int chooseInt(int maxValue);

    //Getter
    public String getName() {
        return name;
    }
    public String getPawn() {
        return pawn;
    }

}
