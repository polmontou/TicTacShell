package fr.campus.view;

import java.util.*;

public class UserInteraction {

    public int getUserInt() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        return sc.nextInt();
    }

    static String getUserString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
