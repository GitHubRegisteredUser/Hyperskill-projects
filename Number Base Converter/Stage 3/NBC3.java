package NumberBaseConverter;

import java.math.BigInteger;
import java.util.Scanner;

public class NBC3 {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            Scanner scanner = new Scanner(System.in);
            String firstInput = scanner.nextLine();

            if (firstInput.equals("/exit")) {
                break;
            } else {
                String[] bases = firstInput.split(" ");
                int sourceBase = Integer.parseInt(bases[0]);
                int targetBase = Integer.parseInt(bases[1]);

                while (true) {
                    System.out.println("Enter number in base " + sourceBase +
                            " to convert to base " + targetBase + " (To go back type /back)");
                    String secondInput = scanner.next();
                    if (secondInput.equals("/back")) {
                        break;
                    } else {
                        BigInteger decimal = new BigInteger(secondInput, sourceBase);
                        String converted = new BigInteger(String.valueOf(decimal)).toString(targetBase);
                        System.out.println("Conversion result: " + converted);
                    }
                }
            }
        }
    }

}
