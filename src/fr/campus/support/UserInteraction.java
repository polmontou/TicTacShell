package fr.campus.support;

import fr.campus.game.tictactoe.TicTacToePawn;
import fr.campus.support.player.BotPlayer;
import fr.campus.support.player.HumanPlayer;
import fr.campus.support.player.Player;

import java.util.*;

public class UserInteraction {

    public static int askForInt(String message, int min, int max) {
        View.message(message);

        int answer = getUserInt();

        while (answer < min || answer > max){
            View.message("Please enter a number between " + min + " and " + max+".");
            answer = getUserInt();
        }
        return answer;
    }

    public static int askForInt(int min, int max) {
        int answer = getUserInt();

        while (answer < min || answer > max){
            View.message("Please enter a number between " + min + " and " + max+".");
            answer = getUserInt();
        }
        return answer;
    }

    public static int getUserInt() {
        Scanner sc = new Scanner(System.in);
        int answer=-5;
        boolean exit = false;

        while(!exit) {
            try {
                answer = sc.nextInt();
                exit = true;
            } catch (InputMismatchException e) {
                sc.nextLine();
                View.message("Integer expected, try again :");
            }
        }

        return answer;
    }

    public static String getUserString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static <E extends Enum<E>> E parseUserChoice(int choice, Class<E> enumClass) {
        choice--;
        E[] options = enumClass.getEnumConstants();

        return options[choice];
    }

    public static Player[] parseUserPlayerChoice(int choice) {
        Player[] players = new Player[2];
        switch (choice) {
            case 1:
                for (int i = 0; i < players.length; i++) {
                    players[i] = new HumanPlayer("Player "+ (i+1), TicTacToePawn.distributePawn(i).getRepresentation());
                }
                break;
            case 2:
                int j = 0;
                players[j] = new HumanPlayer("Player "+ (j+1), TicTacToePawn.distributePawn(j).getRepresentation());
                players[j+1] = new BotPlayer("Bot "+j,  TicTacToePawn.distributePawn(j+1).getRepresentation());
                break;
            case 3:
                for (int i = 0; i < players.length; i++) {
                    players[i] = new BotPlayer("Bot "+ (i+1), TicTacToePawn.distributePawn(i).getRepresentation());
                }
        }
        List<Player> shufflableList= Arrays.asList(players);
        Collections.shuffle(shufflableList);
        shufflableList.toArray(players);

        return players;
    }
}
