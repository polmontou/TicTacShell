package fr.campus.support.player;

public class BotPlayer extends Player {

    public BotPlayer(String name, String pawn) {
        super(name, pawn);
    }

    public int chooseInt(int minValue, int maxValue){
        int answer;
        do {
            answer = (int)(Math.random() * maxValue);
        } while (answer < minValue);
            return answer;
    }
}
