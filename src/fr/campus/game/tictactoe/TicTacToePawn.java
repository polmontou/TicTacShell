package fr.campus.game.tictactoe;

public enum TicTacToePawn {
    X("X"),
    O("O");

    private final String representation;

    TicTacToePawn(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public static TicTacToePawn distributePawn(int i) {
        return TicTacToePawn.values()[i%2];
    }


}
