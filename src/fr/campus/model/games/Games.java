package fr.campus.model.games;

public enum Games {
    TICTACTOE("TicTacToe"),
    PUISSANCE4("Puissance 4"),
    GOMOKU("Gomoku"),
    FREESTYLE ("Freestyle");
    
    private String name;

    Games(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
