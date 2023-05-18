package Hyperskill.AmazingNumbers;

import java.util.Scanner;

public class AN8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to amazing numbers!");
        String sr = ("""
                        Supported requests:
                        - enter a natural number to know its properties;
                        - enter two natural numbers to obtain the properties of the list:
                          * the first parameter represents a starting number;
                          * the second parameter shows how many consecutive numbers are to be printed;
                        - two natural numbers and properties to search for;
                        - a property preceded by minus must not be present in numbers;
                        - separate the parameters with one space;
                        - enter 0 to exit.
                        """);
        System.out.println(sr);
        String properties = "buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad, even, odd";
        String[] aP = {"buzz", "duck", "palindromic",
            "gapful", "spy", "square", "sunny", "jumping", "happy", "sad", "even", "odd"};

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
            if (array.length >= 2) {
                n2 = Long.parseLong(array[1]);
            }
            long count = 0;
            long countW1 = 0;
            long countW2 = 0;

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

            if (array.length == 1) {
                if (number > 0 && n2 == 0) {
                    showProperties1(number);
                }
            }

            if (array.length == 2) {
                if (number > 0 && n2 == 0) {
                    showProperties1(number);
                } else if (number > 0 && n2 > 0) {
                    for (long i = n1; number < n1 + n2; i++, number++) {
                        System.out.println(showProperties2(number));
                    }
                }
            }

            if (array.length >= 3) {
                for (int i = 2; i < array.length; i++) {
                    if (!properties.contains(array[i].toLowerCase())
                            && !properties.contains(array[i].toLowerCase().replace("-", ""))) {
                        countW1++;
                    }
                }
            }
            if (countW1 == 1) {
                System.out.print("The property [");
                for (int j = 2; j < array.length; j++) {
                    if (!properties.contains(array[j].toLowerCase())) {
                        System.out.print(array[j].toUpperCase());
                    }
                }
                System.out.println("] is wrong.");
                System.out.print("Available properties: [");
                for (int m = 0; m < aP.length; m++) {
                    if (m == aP.length - 1) {
                        System.out.print(aP[m].toUpperCase());
                    } else {
                        System.out.print(aP[m].toUpperCase() + ", ");
                    }
                }
                System.out.println("]");
            } else if (countW1 > 1) {
                System.out.print("The properties [");
                for (int k = 2; k < array.length; k++) {
                    if (!properties.contains(array[k].toLowerCase()) && countW2 == countW1 - 1) {
                        System.out.print(array[k].toUpperCase());
                    } else if (!properties.contains(array[k].toLowerCase()) && countW2 != countW1 - 1) {
                        System.out.print(array[k].toUpperCase() + ", ");
                        countW2++;
                    }
                }
                System.out.println("] are wrong.");
                System.out.print("Available properties: [");
                for (int n = 0; n < aP.length; n++) {
                    if (n == aP.length - 1) {
                        System.out.print(aP[n].toUpperCase());
                    } else {
                        System.out.print(aP[n].toUpperCase() + ", ");
                    }
                }
                System.out.println("]");
            }

            if (checkExclusive1(input) || checkExclusive2(array)) {
                continue;
            }

            if (array.length >= 3 && countW1 == 0) {
                Outer:
                for (long i = n1; count < n2; i++, number++) {
                    String c = showProperties2(number);
                    Inner:
                    for (int j = 2; j < array.length; j++) {
                        if (array[j].toLowerCase().contains("-")) {
                            if (c.contains(array[j].toLowerCase().replace("-", ""))) {
                                continue Outer;
                            }
                        } else {
                            if (!c.contains(array[j].toLowerCase())) {
                                continue Outer;
                            }
                        }
                    }
                    count++;
                    System.out.println(showProperties2(number));
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

    static boolean isJumping(long a) {
        String s = Long.toString(a);
        if (s.length() > 1) {
            for (int i = 0; i < s.length() - 1; i++) {
                if (Math.abs(s.charAt(i) - s.charAt(i + 1)) != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isHappy(long a) {
        long rem = 0;
        long sum = 0;
        while (true) {
            while (true) {
                rem = a % 10;
                sum += (rem * rem);
                a = a / 10;
                if (a == 0) {
                    a = sum;
                    sum = 0;
                    break;
                }
            }
            if (a == 1) {
                return true;
            } else if (a == 4) {
                return false;
            }
        }
    }

    static boolean isSad(long a) {
        return (!isHappy(a));
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
        System.out.println("jumping: " + isJumping(a));
        System.out.println("happy: " + isHappy(a));
        System.out.println("sad: " + isSad(a));
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
        if (isJumping(a)) {
            res += "jumping, ";
        }
        if (isHappy(a)) {
            res += "happy, ";
        }
        if (isSad(a)) {
            res += "sad, ";
        }
        if (isEven(a)) {
            res += "even, ";
        } else {
            res += "odd, ";
        }
        res = res.substring(0, res.length() - 2);
        return res;
    }

    static boolean checkExclusive1(String s) {
        if (s.toLowerCase().contains("odd") && s.toLowerCase().contains("even")
                && (!s.toLowerCase().contains("-odd") && !s.toLowerCase().contains("-even"))) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]");
            return true;
        } else if (s.toLowerCase().contains("duck") && s.toLowerCase().contains("spy")
                && (!s.toLowerCase().contains("-duck") && !s.toLowerCase().contains("-spy"))) {
            System.out.println("The request contains mutually exclusive properties: [SPY, DUCK]");
            return true;
        } else if (s.toLowerCase().contains("square") && s.toLowerCase().contains("sunny")
                && (!s.toLowerCase().contains("-square") && !s.toLowerCase().contains("-sunny"))) {
            System.out.println("The request contains mutually exclusive properties: [SQUARE, SUNNY]");
            return true;
        } else if (s.toLowerCase().contains("happy") && s.toLowerCase().contains("sad")
                && (!s.toLowerCase().contains("-happy") && !s.toLowerCase().contains("-sad"))) {
            System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]");
            return true;
        }
        if (s.toLowerCase().contains("-odd") && s.toLowerCase().contains("-even")) {
            System.out.println("The request contains mutually exclusive properties: [-ODD, -EVEN]");
            return true;
        } else if (s.toLowerCase().contains("-happy") && s.toLowerCase().contains("-sad")) {
            System.out.println("The request contains mutually exclusive properties: [-HAPPY, -SAD]");
            return true;
        }
        return false;
    }

    static boolean checkExclusive2(String[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].contains("-")) {
                    if (array[i].replace("-", "").equals(array[j])) {
                        System.out.println("The request contains mutually exclusive properties: ["
                                + array[i].toUpperCase() + ", " + array[j].toUpperCase() + "]");
                        return true;
                    }
                } else {
                    if (array[j].contains("-")) {
                        if (array[i].equals(array[j].replace("-", ""))) {
                            System.out.println("The request contains mutually exclusive properties: ["
                                    + array[i].toUpperCase() + ", " + array[j].toUpperCase() + "]");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
