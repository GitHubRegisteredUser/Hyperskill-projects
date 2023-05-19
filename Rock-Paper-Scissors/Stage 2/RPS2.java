package Hyperskill.RockPaperScissors;

import java.util.Scanner;
import java.util.Random;

public class RPS2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        Random random = new Random();
        int r = random.nextInt(3);
        switch (r) {
            case 0:
                if (input.equals("paper")) {
                    System.out.println("Well done. The computer chose rock and failed");
                    break;
                } else if (input.equals("rock")) {
                    System.out.println("There is a draw (rock)");
                    break;
                } else {
                    System.out.println("Sorry, but the computer chose rock");
                    break;
                }
            case 1:
                if (input.equals("scissors")) {
                    System.out.println("Well done. The computer chose paper and failed");
                    break;
                } else if (input.equals("paper")) {
                    System.out.println("There is a draw (paper)");
                    break;
                } else {
                    System.out.println("Sorry, but the computer chose paper");
                    break;
                }
            case 2:
                if (input.equals("rock")) {
                    System.out.println("Well done. The computer chose scissors and failed");
                    break;
                } else if (input.equals("scissors")) {
                    System.out.println("There is a draw (scissors)");
                    break;
                } else {
                    System.out.println("Sorry, but the computer chose scissors");
                    break;
                }
        }
    }

}
