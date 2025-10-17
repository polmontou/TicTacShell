package fr.campus.support;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Tool {

    public static void message(String message){
        System.out.println(message);
    }

    public static int askForInt(String message, int min, int max) {
        message(message);

        int answer = getUserInt();

        while (answer < min || answer > max){
            message("Please enter a number between " + min + " and " + max+".");
            answer = getUserInt();
        }
        return answer;
    }

    public static int askForInt(int min, int max) {
        int answer = getUserInt();

        while (answer < min || answer > max){
            message("Please enter a number between " + min + " and " + max+".");
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
                message("Integer expected, try again :");
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
}
