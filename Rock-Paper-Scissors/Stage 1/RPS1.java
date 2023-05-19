package Hyperskill.RockPaperScissors;

import java.util.Scanner;

public class RPS1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        switch (input) {
            case "paper":
                System.out.println("Sorry, but the computer chose scissors");
                break;
            case "scissors":
                System.out.println("Sorry, but the computer chose rock");
                break;
            case "rock":
                System.out.println("Sorry, but the computer chose paper");
                break;
        }
    }

}
