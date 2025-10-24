package fr.campus.view;

import java.util.*;

public class UserInteraction {

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
                menu.showLog(("Integer expected, try again :");
            }
        }

        return answer;
    }

    static String getUserString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
