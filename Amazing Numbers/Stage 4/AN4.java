package Hyperskill.AmazingNumbers;

import java.util.Scanner;

public class AN4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to amazing numbers!");
        String sr = ("""
                           Supported requests:
                           - enter a natural number to know its properties;
                           - enter two natural numbers to obtain the properties of the list:
                             * the first parameter represents a starting number;
                             * the second parameter shows how many consecutive numbers are to be processed;
                           - separate the parameters with one space;
                           - enter 0 to exit.
                           """);
        System.out.println(sr);

        while (true) {
            System.out.print("Enter a request:");
            String input = scanner.nextLine();
            String[] array = input.split(" ");
            if (array[0].equals("")) {
                System.out.println(sr);
                continue;
            }
            long number = Long.parseLong(array[0]);
            long n1 = number;
            long n2 = 0;
            if (array.length == 2) {
                n2 = Long.parseLong(array[1]);
            }

            if (number == 0) {
                System.out.println("Goodbye!");
                System.out.println("Process finished with exit code 0");
                return;
            }
            if (number < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else if (n2 < 0) {
                System.out.println("The second parameter should be a natural number or zero.");
            }

            if (number > 0 && n2 == 0) {
                System.out.println("Properties of " + number);
                System.out.println("buzz: " + isBuzz(number));
                System.out.println("duck: " + isDuck(number));
                System.out.println("palindromic: " + isPalindromic(number));
                System.out.println("gapful: " + isGapful(number));
                System.out.println("even: " + isEven(number));
                System.out.println("odd: " + isOdd(number));
            } else if (number > 0 && n2 > 0) {
                for (long i = n1; number < n1 + n2; i++, number++) {
                    System.out.println(showProperties(number));
                }
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

    static boolean isGapful(long a) {
        String s = Long.toString(a);
        String g = "" + s.charAt(0) + s.charAt(s.length() - 1);
        long b = Long.parseLong(g);
        return a >= 100 && a % b == 0;
    }

    static String showProperties(long a) {
        String res = (a + " is ");
        if (isBuzz(a)) {
            res += "buzz, ";
        }
        if (isDuck(a)) {
            res += "duck, ";
        }
        if (isPalindromic(a)) {
            res += "palindromic, ";
        }
        if (isGapful(a)) {
            res += "gapful, ";
        }
        if (isEven(a)) {
            res += "even, ";
        } else {
            res += "odd, ";
        }
        res = res.substring(0, res.length() - 2);
        return res;
    }

}
