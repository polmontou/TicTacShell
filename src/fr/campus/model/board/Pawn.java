package fr.campus.model.board;

/**
 * Enumeration representing the different pawn types in the game.
 * Each pawn has a visual representation.
 */
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

    /**
     * Distributes pawns alternately based on player index.
     *
     * @param i The player index
     * @return X for even indices, O for odd indices
     */
    public static Pawn distributePawn(int i) {
        return Pawn.values()[i%2];
    }
}