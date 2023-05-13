package Hyperskill.ChuckNorrisCipherEncoder;

import java.util.Scanner;

public class CNCE3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < 32) {
                sb.append("00".concat(Integer.toBinaryString(input.charAt(i))));
            } else if (input.charAt(i) < 64) {
                sb.append("0".concat(Integer.toBinaryString(input.charAt(i))));
            } else {
                sb.append(Integer.toBinaryString(input.charAt(i)));
            }
        }
        String binary = sb.toString();
        int bL = binary.length();

        System.out.println("The result:");
        Outer:
        for (int i = 0; i < bL; i++) {
            if (binary.charAt(i) == 48) {
                System.out.print("00 0");
            } else if (binary.charAt(i) == 49) {
                System.out.print("0 0");
            }
            Inner:
            for (int j = i + 1; j < bL; j++) {
                if (j == bL - 1 && binary.charAt(bL - 1) == binary.charAt(bL - 2)) {
                    System.out.println('0');
                    break Outer;
                }
                if (binary.charAt(i) == binary.charAt(j)) {
                    System.out.print('0');
                } else {
                    i = j - 1;
                    System.out.print(" ");
                    break;
                }
            }
        }
    }

}
