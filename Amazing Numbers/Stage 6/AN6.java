package Hyperskill.AmazingNumbers;

import java.util.Scanner;
import java.util.Arrays;

public class AN6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to amazing numbers!");
        String sr = ("""
                        Supported requests:
                        - enter a natural number to know its properties; 
                        - enter two natural numbers to obtain the properties of the list:
                          * the first parameter represents a starting number;
                          * the second parameter shows how many consecutive numbers are to be printed;
                        - two natural numbers and a property to search for;
                        - two natural numbers and two properties to search for;
                        - separate the parameters with one space;
                        - enter 0 to exit.
                        """);
        System.out.println(sr);
        String[] properties = {"buzz", "duck", "palindromic",
            "gapful", "spy", "even", "odd", "square", "sunny"};

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
            String p1 = "";
            String p2 = "";
            switch (array.length) {
                case 2:
                    n2 = Long.parseLong(array[1]);
                    break;
                case 3:
                    n2 = Long.parseLong(array[1]);
                    p1 += array[2];
                    break;
                case 4:
                    n2 = Long.parseLong(array[1]);
                    p1 += array[2];
                    p2 += array[3];
                    break;
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
            } else if (number > 0 && n2 > 0 && p1.equals("")) {
                for (long i = n1; number < n1 + n2; i++, number++) {
                    System.out.println(showProperties2(number));
                }
            }

            if (!p1.equals("") && !check1(properties, p1)) {
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
            } else if (!p1.equals("") && !check1(properties, p2) && !p2.equals("")) {
                System.out.println("The property [" + array[3].toUpperCase() + "] is wrong.");
                System.out.print("Available properties: [");
                for (int i = 0; i < properties.length; i++) {
                    if (i == properties.length - 1) {
                        System.out.print(properties[i].toUpperCase());
                    } else {
                        System.out.print(properties[i].toUpperCase() + ", ");
                    }
                }
                System.out.println("]");
            } 
            if (!p1.equals("") && !check2(properties, p1, p2) && !p2.equals("")) {
                System.out.println("The properties [" + array[2].toUpperCase() + ", " + array[3].toUpperCase() + "] are wrong.");
                System.out.print("Available properties: [");
                for (int i = 0; i < properties.length; i++) {
                    if (i == properties.length - 1) {
                        System.out.print(properties[i].toUpperCase());
                    } else {
                        System.out.print(properties[i].toUpperCase() + ", ");
                    }
                }
                System.out.println("]");
            }

            if (!p1.equals("") && check1(properties, p1) && p2.equals("")) {
                for (long i = n1; count < n2; i++, number++) {
                    String c = showProperties2(number);
                    if (c.contains(p1.toLowerCase())) {
                        count++;
                        System.out.println(showProperties2(number));
                    }
                }
            } else if (!p1.equals("") && check2(properties, p1, p2) && !p2.equals("")) {
                for (long i = n1; count < n2; i++, number++) {
                    String c = showProperties2(number);
                    if (checkExclusive(p1, p2)) {
                        System.out.println("The request contains mutually exclusive properties: [" + p1.toUpperCase() + ", " + p2.toUpperCase() + "]");
                        System.out.println("There are no numbers with these properties.");
                        break;
                    } else if (c.contains(p1.toLowerCase()) && c.contains(p2.toLowerCase())) {
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

    static boolean isSquare(long a) {
        return (long) Math.pow((long) Math.sqrt(a), 2) == a;
    }

    static boolean isSunny(long a) {
        return isSquare(a + 1);
    }

    static void showProperties1(long a) {
        System.out.println("Properties of " + a);
        System.out.println("buzz: " + isBuzz(a));
        System.out.println("duck: " + isDuck(a));
        System.out.println("palindromic: " + isPalindromic(a));
        System.out.println("gapful: " + isGapful(a));
        System.out.println("spy: " + isSpy(a));
        System.out.println("square: " + isSquare(a));
        System.out.println("sunny: " + isSunny(a));
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
        if (isSquare(a)) {
            res += "square, ";
        }
        if (isSunny(a)) {
            res += "sunny, ";
        }
        if (isEven(a)) {
            res += "even, ";
        } else {
            res += "odd, ";
        }
        res = res.substring(0, res.length() - 2);
        return res;
    }

    static boolean check1(String[] array, String s) {
        return Arrays.asList(array).contains(s.toLowerCase());
    }

    static boolean check2(String[] array, String s1, String s2) {
        return Arrays.asList(array).contains(s1.toLowerCase())
                && Arrays.asList(array).contains(s2.toLowerCase());
    }

    static boolean checkExclusive(String s1, String s2) {
        return (s1.toLowerCase().equals("even") && s2.toLowerCase().equals("odd"))
                || (s1.toLowerCase().equals("odd") && s2.toLowerCase().equals("even"))
                || (s1.toLowerCase().equals("duck") && s2.toLowerCase().equals("spy"))
                || (s1.toLowerCase().equals("spy") && s2.toLowerCase().equals("duck"))
                || (s1.toLowerCase().equals("square") && s2.toLowerCase().equals("sunny"))
                || (s1.toLowerCase().equals("sunny") && s2.toLowerCase().equals("square"));
    }

}
