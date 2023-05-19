package Hyperskill.RockPaperScissors;

import java.util.Scanner;
import java.util.Random;

public class RPS3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            String input = scanner.next();
            int r = random.nextInt(3);
            if (input.contains("!exit")) {
                System.out.println("Bye!");
                break;
            } else if (!input.equals("rock") && !input.equals("paper") && !input.equals("scissors")) {
                System.out.println("Invalid input");
                continue;
            }

            switch (input) {
                case "rock":
                    if (r == 0) {
                        System.out.println("There is a draw (rock)");
                        break;
                    } else if (r == 1) {
                        System.out.println("Sorry, but the computer chose paper");
                        break;
                    } else {
                        System.out.println("Well done. The computer chose scissors and failed");
                        break;
                    }
                case "paper":
                    if (r == 0) {
                        System.out.println("Well done. The computer chose rock and failed");
                        break;
                    } else if (r == 1) {
                        System.out.println("There is a draw (paper)");
                        break;
                    } else {
                        System.out.println("Sorry, but the computer chose scissors");
                        break;
                    }
                case "scissors":
                    if (r == 0) {
                        System.out.println("Sorry, but the computer chose rock");
                        break;
                    } else if (r == 1) {
                        System.out.println("Well done. The computer chose paper and failed");
                        break;
                    } else {
                        System.out.println("There is a draw (scissors)");
                        break;
                    }
            }
        }
    }

}
