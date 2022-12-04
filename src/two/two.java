package two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class two {
    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSORS = 3;

    private static final int LOSE = 0;
    private static final int DRAW = 3;
    private static final int WIN = 6;

    public static int run1() {
        Scanner sc = null;
        try{
            sc = new Scanner(new File("src/two/input.txt"));
        } catch (FileNotFoundException e) { throw new RuntimeException(e); }
        int score = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            score += getScore(line);
        }
        return score;
    }
    public static int run2(){
        Scanner sc = null;
        try{
            sc = new Scanner(new File("src/two/input.txt"));
        } catch (FileNotFoundException e) { throw new RuntimeException(e); }
        int score = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            score += getScore2(line);
        }
        return score;
    }
    public static int getScore(String s) {
        int p1 = s.charAt(0) == 'A' ? ROCK : s.charAt(0) == 'B' ? PAPER : SCISSORS;
        int p2 = s.charAt(2) == 'X' ? ROCK : s.charAt(2) == 'Y' ? PAPER : SCISSORS;
        if (p1 == p2) return DRAW + p2;
        if (p1 == SCISSORS && p2 == ROCK) return WIN + p2;
        if (p1 == ROCK && p2 == PAPER) return WIN + p2;
        if (p1 == PAPER && p2 == SCISSORS) return WIN + p2;
        return p2;
    }
    public static int getScore2(String s) {
        int p1 = s.charAt(0) == 'A' ? ROCK : s.charAt(0) == 'B' ? PAPER : SCISSORS;
        int p2 = s.charAt(2) == 'X' ? LOSE : s.charAt(2) == 'Y' ? DRAW : WIN;
        switch(p2) {
            case LOSE -> {
                return switch(p1){
                    case ROCK -> SCISSORS;
                    case PAPER -> ROCK;
                    case SCISSORS -> PAPER;
                    default -> throw new IllegalStateException("Unexpected value: " + p1);
                };
            }
            case DRAW -> { return DRAW + p1; }
            case WIN -> {
                return switch(p1){
                    case ROCK -> WIN + PAPER;
                    case PAPER -> WIN + SCISSORS;
                    case SCISSORS -> WIN + ROCK;
                    default -> throw new IllegalStateException("Unexpected value: " + p1);
                };
            }
        }
        return -1;
    }
}