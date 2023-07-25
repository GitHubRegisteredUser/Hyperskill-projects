package NumberBaseConverter;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class NBC4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            String input = scanner.nextLine();
            if (input.equals("/exit")) {
                break;
            } else {
                convert(input.split(" "));
            }
        }
    }

    public static void convert(String[] bases) {
        Scanner scanner = new Scanner(System.in);
        int sourceBase = Integer.parseInt(bases[0]);
        int targetBase = Integer.parseInt(bases[1]);
        boolean useBigDecimal = false;

        while (true) {
            System.out.println("Enter number in base " + sourceBase +
                    " to convert to base " + targetBase + " (To go back type /back)");
            String input = scanner.nextLine();
            if (input.equals("/back")) {
                break;
            }
            for (char c : input.toCharArray()) {
                if (c == '.') {
                    useBigDecimal = true;
                    break;
                }
            }

            if (useBigDecimal) {
                String[] parts = input.split("\\.");
                BigInteger firstPart = new BigInteger(parts[0], sourceBase);
                BigDecimal secondPart = new BigDecimal("0.0");
                int count = -1;
                for (String s : parts[1].split("")) {
                    secondPart = secondPart.add(new BigDecimal(Math.pow(sourceBase, count)
                            * Integer.parseInt(s, sourceBase)));
                    count--;
                    if (count < -16) {
                        break;
                    }
                }

                double quotient = (secondPart.doubleValue() * Math.pow(targetBase, 8));
                List<Integer> list = new ArrayList<>();
                while (quotient >= 1) {
                    list.add((int) Math.floor(quotient % targetBase));
                    quotient /= targetBase;
                }
                while (list.size() < 8) {
                    list.add(0);
                }

                StringBuilder sb = new StringBuilder();
                for (int i = list.size() - 1; i >= 0; i--) {
                    sb.append(Integer.toString(list.get(i), targetBase));
                }
                while (sb.length() < 5) {
                    sb.append(0);
                }
                System.out.print("Conversion result: ");
                System.out.println(firstPart.toString(targetBase) + "." + sb.substring(0, 5));
            } else {
                BigInteger decimal = new BigInteger(input, sourceBase);
                String converted = new BigInteger(String.valueOf(decimal)).toString(targetBase);
                System.out.println("Conversion result: " + converted);
            }
            useBigDecimal = false;
        }
    }

}
