package NumberBaseConverter;

import java.util.Scanner;

public class NBC2 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
            String choice = scanner.next();

            if (choice.equals("/from")) {
                convert();
            } else if (choice.equals("/to")) {
                convertToDecimal();
            } else {
                break;
            }
        }
    }

    static void convert() {
        System.out.println("Enter a number in decimal system: ");
        int number = scanner.nextInt();
        System.out.println("Enter the target base: ");
        int base = scanner.nextInt();
        String result = null;
        switch (base) {
            case 2:
                result = Integer.toBinaryString(number);
                break;
            case 8:
                result = Integer.toOctalString(number);
                break;
            case 16:
                result = Integer.toHexString(number);
                break;
        }
        System.out.println("Conversion result: " + result);
    }

    static void convertToDecimal() {
        System.out.println("Enter source number: ");
        String number = scanner.next();
        System.out.println("Enter source base: ");
        int base = scanner.nextInt();
        System.out.println("Conversion to decimal result: " + Integer.parseInt(number, base));
    }

}
