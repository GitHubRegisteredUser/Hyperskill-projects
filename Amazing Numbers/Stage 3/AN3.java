package Hyperskill.AmazingNumbers;

import java.util.Scanner;

public class AN3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.");
        while (true) {
            System.out.print("Enter a request:");
            long number = scanner.nextLong();
            if (number < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else if (number > 0) {
                System.out.println("Properties of " + number);
                System.out.println("even: " + isEven(number));
                System.out.println("odd: " + isOdd(number));
                System.out.println("Buzz: " + isBuzz(number));
                System.out.println("Duck: " + isDuck(number));
                System.out.println("palindromic: " + isPalindromic(number));
            }
            if (number == 0) {
                System.out.println("Goodbye!");
                return;
            }
        }
    }

    static boolean isEven(long a) {
        return a % 2 == 0;
    }

    static boolean isOdd(long a) {
        return !isEven(a);
    }

    static boolean isBuzz(long a) {
        return a % 7 == 0 || a % 10 == 7;
    }

    static boolean isDuck(long a) {
        String s = Long.toString(a);
        return s.charAt(0) != 0 && s.contains("0");
    }

    static boolean isPalindromic(long a) {
        String s = Long.toString(a);
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }
        return s.equals(result);
    }
    
}
