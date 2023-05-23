package Hyperskill.ReadabilityScore;

import java.util.Scanner;

public class RS1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input.length() > 100 ? "HARD" : "EASY");
    }

}
