package Hyperskill.ChuckNorrisCipherEncoder;

import java.util.Scanner;

public class CNCE1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i) + " ");
        }
    }

}
