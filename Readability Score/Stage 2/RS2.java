package Hyperskill.ReadabilityScore;

import java.util.Scanner;

public class RS2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] sentences = input.split("[.!?]");
        int sum = 0;
        for (int i = 0; i < sentences.length; i++) {
            sum += sentences[i].split(" ").length;
        }
        System.out.println(sum / sentences.length > 10 ? "HARD" : "EASY");
    }

}
