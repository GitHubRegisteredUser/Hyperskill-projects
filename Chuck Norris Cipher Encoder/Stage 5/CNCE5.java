package Hyperskill.ChuckNorrisCipherEncoder;

import java.util.Scanner;

public class CNCE5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String choice = scanner.nextLine();
            switch (choice) {
                case "encode":
                    encode();
                    break;
                case "decode":
                    decode();
                    break;
                case "exit":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("There is no '" + choice + "' operation");
            }
        }
    }

    static void encode() {
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

        System.out.println("Encoded string:");
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
        System.out.println();
    }

    static void decode() {
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        System.out.println("Input encoded string:");
        String input = scanner.nextLine();
        
        for (int k = 0; k < input.length(); k++) {
            if (input.charAt(k) != 48 && input.charAt(k) != 32) {
                b = false;
                break;
            }
        }
        
        String[] array = input.split(" ");
        String binary = "";
        
        if (array.length % 2 == 1) {
            b = false;
        }
        
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
            } else {
                b = false;
                break;
            }
        }
        
        if (binary.length() % 7 !=0) {
            b = false;
        }
        
        if (b) {
            String[] arr = binary.split("(?<=\\G.{" + 7 + "})");
            System.out.println("Decoded string:");
            for (String s : arr) {
                System.out.print((char) Integer.parseInt(s, 2));
            }
            System.out.println();
        } else {
            System.out.println("Encoded string is not valid");
        }
    }

}
