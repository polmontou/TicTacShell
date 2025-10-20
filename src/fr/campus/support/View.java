package fr.campus.support;

import fr.campus.game.board.Board;
import fr.campus.support.player.Player;


public class View {
    public static void message(String message){
        System.out.println(message);
    }

    public static void displayBoard(Board board,int lines, int col) {
        System.out.print("  |");
        for (int l = 0; l < col; l++) {
            System.out.print(" " + (l + 1) + " |");
        }
        System.out.println();
        System.out.print("---");
        for (int k = 0; k < col; k++) {
            System.out.print("----");
        }
        System.out.println();
        for (int i = 0; i < lines; i++) {
            System.out.print(i + 1 + " |");
            for (int j = 0; j < col; j++) {
                System.out.print(board.getCell(i, j).toString() + "|");
            }
            System.out.println();
            System.out.print("---");
            for (int k = 0; k < col; k++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

    public static void displayBoard(Board board, int boardSize) {
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
                System.out.print(board.getCell(i, j).toString()+"|");
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
