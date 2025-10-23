package fr.campus.view;

import java.util.*;

public class UserInteraction {

    static int askForInt(String message, int min, int max) {
        View.message(message);

        int answer = getUserInt();

        while (answer < min || answer > max){
            View.message("Please enter a number between " + min + " and " + max+".");
            answer = getUserInt();
        }
        return answer;
    }

    private static int getUserInt() {
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

    static String getUserString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
