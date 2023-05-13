package Hyperskill.ChuckNorrisCipherEncoder;

import java.util.Scanner;

public class CNCE4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        String[] array = input.split(" ");
        String binary = "";
        for (int i = 1; i < array.length; i += 2) {
            if (array[i - 1].equals("0")) {
                array[i] = array[i].replace('0', '1');
                for (int j = 0; j < array[i].length(); j++) {
                    binary += (array[i].charAt(j));
                }
            } else if (array[i - 1].equals("00")) {
                for (int j = 0; j < array[i].length(); j++) {
                    binary += (array[i].charAt(j));
                }
            }
        }
        String[] arr = binary.split("(?<=\\G.{" + 7 + "})");

        System.out.println("The result:");
        for (String s : arr) {
            System.out.print((char) Integer.parseInt(s, 2));
        }
    }

}
