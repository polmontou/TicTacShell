package fr.campus.support;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Tool {

    public static void message(String message){
        System.out.println(message);
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
                message("Integer expected, try again :");
            }
        }

        return answer;
    }

    public static String getUserString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
