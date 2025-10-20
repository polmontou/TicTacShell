package fr.campus.game.board;

public enum Pawn {
    X("X"),
    O("O");

    private final String representation;

    Pawn(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public static Pawn distributePawn(int i) {
        return Pawn.values()[i%2];
    }


}
