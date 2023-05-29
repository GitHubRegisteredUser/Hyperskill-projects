package Hyperskill.ReadabilityScore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class RS4 {

    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0] != null) {
            try {
                String input = new String(Files.readAllBytes(Paths.get(args[0])));
                Scanner scanner = new Scanner(System.in);

                System.out.println("The text is:");
                System.out.println(input);
                System.out.println("Words: " + countW(input));
                System.out.println("Sentences: " + countSe(input));
                System.out.println("Characters: " + countC(input));
                System.out.println("Syllables: " + countSy(input));
                System.out.println("Polysyllables: " + countPsy(input));

                System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
                System.out.println("");
                String choice = scanner.next();
                switch (choice) {
                    case "ARI":
                        pARI(input);
                        break;
                    case "FK":
                        pFK(input);
                        break;
                    case "SMOG":
                        pSMOG(input);
                        break;
                    case "CL":
                        pCL(input);
                        break;
                    case "all":
                        int ageARI = pARI(input);
                        int ageFK = pFK(input);
                        int ageSMOG = pSMOG(input);
                        int ageCL = pCL(input);
                        double ageAvg = (ageARI + ageFK + ageSMOG + ageCL) / 4;
                        System.out.println("This text should be understood in average by "
                                + String.format("%.2f", ageAvg) + "-year-olds.");
                        break;
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    static int countSe(String i) {
        String[] sentences = i.split("[.!?]");
        int s = sentences.length;
        return s;
    }

    static int countW(String i) {
        String[] words = i.split(" ");
        int w = words.length;
        return w;
    }

    static int countSy(String i) {
        String[] words = i.split(" ");
        for (int j = 0; j < words.length; j++) {
            String w = words[j].toLowerCase();
            if (w.charAt(w.length() - 1) == 101) {
                w = w.substring(0, w.length() - 1);
            }
            while (true) {
                if (w.matches(".*[aeiouy]{2}.*")) {
                    w = w.replaceAll("[aeiouy]{2}", "a");
                } else {
                    break;
                }
            }
            w = w.replaceAll("[^aeiouy]", "");
            words[j] = w;
        }
        int s = 0;
        for (int k = 0; k < words.length; k++) {
            s += words[k].length();
        }
        return s < 0 ? 1 : s;
    }

    static int countPsy(String i) {
        String[] words = i.split(" ");
        for (int j = 0; j < words.length; j++) {
            String w = words[j].toLowerCase();
            if (w.charAt(w.length() - 1) == 101) {
                w = w.substring(0, w.length() - 1);
            }
            while (true) {
                if (w.matches(".*[aeiouy]{2}.*")) {
                    w = w.replaceAll("[aeiouy]{2}", "a");
                } else {
                    break;
                }
            }
            w = w.replaceAll("[^aeiouy]", "");
            words[j] = w;
        }
        int pS = 0;
        for (int k = 0; k < words.length; k++) {
            if (words[k].length() > 2) {
                pS++;
            }
        }
        return pS < 0 ? 1 : pS;
    }

    static int countC(String i) {
        String[] words = i.split(" ");
        int c = 0;
        for (int j = 0; j < words.length; j++) {
            c += words[j].length();
        }
        return c;
    }

    static double calcARI(int s, int w, int c) {
        double score = 4.71 * (1.0 * c / w) + 0.5 * (1.0 * w / s) - 21.43;
        return score;
    }

    static double calcFK(int se, int w, int sy) {
        double score = 0.39 * (1.0 * w / se) + 11.8 * (1.0 * sy / w) - 15.59;
        return score;
    }

    static double calcSMOG(int se, int psy) {
        double score = 1.043 * Math.sqrt(psy * (30 / se)) + 3.1291;
        return score;
    }

    static double calcCL(int se, int w, int c) {
        double l = 1.0 * c / w * 100;
        double s = 1.0 * se / w * 100;
        double score = 0.0588 * l - 0.296 * s - 15.8;
        return score;
    }

    static int calcAge(double s) {
        int r = 0;
        for (int i = 1; i <= 14; i++) {
            if (i == 14 && i == (int) Math.round(s)) {
                r = i + 8;
                break;
            } else if (i < 14 && i == (int) Math.round(s)) {
                r = i + 5;
                break;
            }
        }
        return r;
    }

    static int pARI(String i) {
        double score = calcARI(countSe(i), countW(i), countC(i));
        int ageARI = calcAge(score);
        System.out.println("Automated Readability Index: "
                + String.format("%.2f", score) + " (about " + ageARI + "-year-olds).");
        return ageARI;
    }

    static int pFK(String i) {
        double score = calcFK(countSe(i), countW(i), countSy(i));
        int ageFK = calcAge(score);
        System.out.println("Flesch–Kincaid readability tests: "
                + String.format("%.2f", score) + " (about " + ageFK + "-year-olds).");
        return ageFK;
    }

    static int pSMOG(String i) {
        double score = calcSMOG(countSe(i), countPsy(i));
        int ageSMOG = calcAge(score);
        System.out.println("Simple Measure of Gobbledygook: "
                + String.format("%.2f", score) + " (about " + ageSMOG + "-year-olds).");
        return ageSMOG;
    }

    static int pCL(String i) {
        double score = calcCL(countSe(i), countW(i), countC(i));
        int ageCL = calcAge(score);
        System.out.println("Coleman–Liau index: "
                + String.format("%.2f", score) + " (about " + ageCL + "-year-olds).");
        return ageCL;
    }

}
