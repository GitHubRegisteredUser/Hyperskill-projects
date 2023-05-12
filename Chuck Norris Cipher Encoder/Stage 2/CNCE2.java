package Hyperskill.ChuckNorrisCipherEncoder;

import java.util.Scanner;

public class CNCE2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        System.out.println("The result:");
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < 32) {
                System.out.println(input.charAt(i) + " = 00" + Integer.toBinaryString(input.charAt(i)));
            } else if (input.charAt(i) < 64) {
                System.out.println(input.charAt(i) + " = 0" + Integer.toBinaryString(input.charAt(i)));
            } else {
                System.out.println(input.charAt(i) + " = " + Integer.toBinaryString(input.charAt(i)));
            }
        }
    }

}
