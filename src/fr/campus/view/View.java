package fr.campus.view;

import fr.campus.model.board.Board;
import fr.campus.model.games.Games;

public class View {
    private Menu menu;

    public View(){
        this.menu = new Menu();
    }

    static void message(String message){
        System.out.println(message);
    }

    public void displayBoard(Board board) {
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
    public int displayMenu(String menuType){
        switch(menuType){
            case "game":
                menu.displayGameChoiceMenu();
                return UserInteraction.askForInt("Which one do you wanna play? (integer expected) : ", 1, Games.values().length);
            case "player":
                menu.displayPlayerChoiceMenu();
                return UserInteraction.askForInt("Which mode do you wanna play? (integer expected) : ", 1, 3);
            default:
                return 0;
        }
    }

    public int askForInt(String message, int min, int max){
        return UserInteraction.askForInt(message, min, max);
    }

}
