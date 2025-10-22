package fr.campus.support;

import fr.campus.game.board.Board;

public class View {
    public static void message(String message){
        System.out.println(message);
    }

    public static void displayBoard(Board board) {
        System.out.print("  |");
        for (int l = 0; l < board.getBoardSizeX(); l++) {
            System.out.print(" " + (l + 1) + " |");
        }
        System.out.println();
        System.out.print("---");
        for (int k = 0; k < board.getBoardSizeX(); k++) {
            System.out.print("----");
        }
        System.out.println();
        for (int i = 0; i < board.getBoardSizeY(); i++) {
            System.out.print(i + 1 + " |");
            for (int j = 0; j < board.getBoardSizeX(); j++) {
                System.out.print(board.getCell(i, j).toString() + "|");
            }
            System.out.println();
            System.out.print("---");
            for (int k = 0; k < board.getBoardSizeX(); k++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

}
