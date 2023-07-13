package NumberBaseConverter;

import java.util.Scanner;

public class NBC1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number in decimal system: ");
        int number = scanner.nextInt();
        System.out.println("Enter target base: ");
        int base = scanner.nextInt();
        System.out.println("Conversion result: " + convert(number, base));
    }

    static String convert(int n, int b) {
        String result = null;
        switch (b) {
            case 2:
                result = Integer.toBinaryString(n);
                break;
            case 8:
                result = Integer.toOctalString(n);
                break;
            case 16:
                result = Integer.toHexString(n);
                break;
        }
        return result;
    }

}
