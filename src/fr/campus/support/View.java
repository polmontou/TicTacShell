package fr.campus.support;

public class View {
    public static void message(String message){
        System.out.println(message);
    }

    public static void displayBoard(Cell[][] board, int boardSize) {
        System.out.print("  |");
        for (int l = 0; l < boardSize; l++) {
            System.out.print(" "+ (l+1) +" |");
        }
        System.out.println();
        System.out.print("---");
        for (int k = 0; k < boardSize; k++) {
            System.out.print("----");
        }
        System.out.println();
        for (int i = 0; i < boardSize; i++) {
            System.out.print(i+1 +" |");
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j].toString()+"|");
            }
            System.out.println();
            System.out.print("---");
            for (int k = 0; k < boardSize; k++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

}
