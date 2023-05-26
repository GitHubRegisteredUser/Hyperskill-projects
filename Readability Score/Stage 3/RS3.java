package Hyperskill.ReadabilityScore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RS3 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        String input = "";
        while (scanner.hasNext()) {
            input += scanner.nextLine();
        }
        double score = calcScore(countS(input), countW(input), countC(input));

        System.out.println("The text is:");
        System.out.println(input);
        System.out.println("Words: " + countW(input));
        System.out.println("Sentences: " + countS(input));
        System.out.println("Characters: " + countC(input));
        System.out.println("The score is: " + String.format("%.2f", score));
        calcAge(score);
    }

    static int countS(String i) {
        String[] sentences = i.split("[.!?]");
        int s = sentences.length;
        return s;
    }

    static int countW(String i) {
        String[] words = i.split(" ");
        int w = words.length;
        return w;
    }

    static int countC(String i) {
        String[] words = i.split(" ");
        int c = 0;
        for (int j = 0; j < words.length; j++) {
            c += words[j].length();
        }
        return c;
    }

    static double calcScore(int s, int w, int c) {
        double score = 4.71 * (1.0 * c / w) + 0.5 * (1.0 * w / s) - 21.43;
        return score;
    }

    static void calcAge(double s) {
        for (int i = 1; i <= 14; i++) {
            if (i == 14 && i == (int) Math.ceil(s)) {
                System.out.printf("This text should be understood by %s-%s year-olds.", i + 4, i + 8);
                break;
            } else if (i < 14 && i == (int) Math.ceil(s)) {
                System.out.printf("This text should be understood by %s-%s year-olds.", i + 4, i + 5);
                break;
            }
        }
    }

}
