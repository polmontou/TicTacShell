package fr.campus.model.games;

public enum Games {
    TICTACTOE("TicTacToe", 3, 3, 3),
    PUISSANCE4("Puissance 4", 4, 6, 7),
    GOMOKU("Gomoku", 5, 15, 15),
    FREESTYLE ("Freestyle", 100);
    
    private String name;
    private int maxSize;
    private int winRule;
    private int lineMax;
    private int columnMax;

    Games(String name, int maxSize) {
        this.name = name;
        this.maxSize = maxSize;
    }

    Games(String name, int winRule, int lineMax, int columnMax) {
        this.name = name;
        this.winRule = winRule;
        this.lineMax = lineMax;
        this.columnMax = columnMax;
    }

//    public GameType createGame(String name,  int winRule, int lineMax, int columnMax) {
//        return GameFactory.createGame(this, name, winRule, lineMax, columnMax);
// }

    //Getters
    public String getName() {
        return name;
    }
    public int getWinRule() {
        return winRule;
    }
    public int getLineMax() {
        return lineMax;
    }
    public int getColumnMax() {
        return columnMax;
    }
    public int getMaxSize() {
        return maxSize;
    }

    public String toString() {
        return this.name;
    }

    //Setters
    public void setWinRule(int winRule) {
        this.winRule = winRule;
    }
    public void setLineMax(int lineMax) {
        this.lineMax = lineMax;
    }
    public void setColumnMax(int columnMax) {
        this.columnMax = columnMax;
    }
}
