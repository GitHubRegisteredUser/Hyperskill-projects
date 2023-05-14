package Hyperskill.AmazingNumbers;

import java.util.Scanner;

public class AN2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number = scanner.nextInt();
        if (number <= 0) {
            System.out.println("This number is not natural!");
        } else {
            System.out.println("Properties of " + number);
            System.out.println("even: " + isEven(number));
            System.out.println("odd: " + isOdd(number));
            System.out.println("Buzz: " + isBuzz(number));
            System.out.println("Duck: " + isDuck(number));
        }
    }

    static boolean isEven(int a) {
        return a % 2 == 0;
    }

    static boolean isOdd(int a) {
        return !isEven(a);
    }

    static boolean isBuzz(int a) {
        return a % 7 == 0 || a % 10 == 7;
    }

    static boolean isDuck(int a) {
        String s = Integer.toString(a);
        return s.charAt(0) != 0 && s.contains("0");
    }

}
