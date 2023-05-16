package Hyperskill.AmazingNumbers;

import java.util.Scanner;
import java.util.Arrays;

public class AN5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to amazing numbers!");
        String sr = ("""
                        Supported requests:
                        - enter a natural number to know its properties;
                        - enter two natural numbers to obtain the properties of the list:
                          * the first parameter represents a starting number;
                          * the second parameter shows how many consecutive numbers are to be processed;
                        - two natural numbers and a property to search for;
                        - separate the parameters with one space;
                        - enter 0 to exit.
                        """);
        System.out.println(sr);
        String[] properties = {"buzz", "duck", "palindromic",
            "gapful", "spy", "even", "odd"};

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
            long count = 0;
            String p = "";
            if (array.length == 2) {
                n2 = Long.parseLong(array[1]);
            } else if (array.length == 3) {
                n2 = Long.parseLong(array[1]);
                p += array[2];
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
                showProperties1(number);
            } else if (number > 0 && n2 > 0 && p.equals("")) {
                for (long i = n1; number < n1 + n2; i++, number++) {
                    System.out.println(showProperties2(number));
                }
            }

            if (!p.equals("") && !check(properties, p)) {
                System.out.println("The property [" + array[2].toUpperCase() + "] is wrong.");
                System.out.print("Available properties: [");
                for (int i = 0; i < properties.length; i++) {
                    if (i == properties.length - 1) {
                        System.out.print(properties[i].toUpperCase());
                    } else {
                        System.out.print(properties[i].toUpperCase() + ", ");
                    }
                }
                System.out.println("]");
            } else if (!p.equals("") && check(properties, p)) {
                for (long i = n1; count < n2; i++, number++) {
                    String c = showProperties2(number);
                    if (c.contains(p.toLowerCase())) {
                        count++;
                        System.out.println(showProperties2(number));
                    }
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

    static long dSum(long a) {
        long sum = 0;
        for (long i = a; i > 0; i /= 10) {
            sum += i % 10;
        }
        return sum;
    }

    static long dProduct(long a) {
        long product = 1;
        for (long i = a; i > 0; i /= 10) {
            product *= i % 10;
        }
        return product;
    }

    static boolean isSpy(long a) {
        return dSum(a) == dProduct(a);
    }

    static void showProperties1(long a) {
        System.out.println("Properties of " + a);
        System.out.println("buzz: " + isBuzz(a));
        System.out.println("duck: " + isDuck(a));
        System.out.println("palindromic: " + isPalindromic(a));
        System.out.println("gapful: " + isGapful(a));
        System.out.println("spy: " + isSpy(a));
        System.out.println("even: " + isEven(a));
        System.out.println("odd: " + isOdd(a));
    }

    static String showProperties2(long a) {
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
        if (isSpy(a)) {
            res += "spy, ";
        }
        if (isEven(a)) {
            res += "even, ";
        } else {
            res += "odd, ";
        }
        res = res.substring(0, res.length() - 2);
        return res;
    }

    static boolean check(String[] array, String s) {
        return Arrays.asList(array).contains(s.toLowerCase());
    }

}
