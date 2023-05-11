package Hyperskill.SimpleTicTacToe;

import java.util.Scanner;

public class STTT3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println("---------");
        System.out.println("| " + input.charAt(0) + " " + input.charAt(1) + " " + input.charAt(2) + " |");
        System.out.println("| " + input.charAt(3) + " " + input.charAt(4) + " " + input.charAt(5) + " |");
        System.out.println("| " + input.charAt(6) + " " + input.charAt(7) + " " + input.charAt(8) + " |");
        System.out.println("---------");

        if (input.contains("_")) {
            if (impCount(input)) {
                System.out.println("Impossible");
            } else if (impossible(input)) {
                System.out.println("Impossible");
            } else if (winsX(input)) {
                System.out.println("X wins");
            } else if (winsO(input)) {
                System.out.println("O wins");
            } else {
                System.out.println("Game not finished");
            }
        } else {
            if (impossible(input)) {
                System.out.println("Impossible");
            } else if (winsX(input)) {
                System.out.println("X wins");
            } else if (winsO(input)) {
                System.out.println("O wins");
            } else {
                System.out.println("Draw");
            }
        }
    }

    static boolean winsX(String s) {
        if ((s.charAt(0) == 'X' && s.charAt(1) == 'X' && s.charAt(2) == 'X')
                || (s.charAt(3) == 'X' && s.charAt(4) == 'X' && s.charAt(5) == 'X')
                || (s.charAt(6) == 'X' && s.charAt(7) == 'X' && s.charAt(8) == 'X')
                || (s.charAt(0) == 'X' && s.charAt(3) == 'X' && s.charAt(6) == 'X')
                || (s.charAt(1) == 'X' && s.charAt(4) == 'X' && s.charAt(7) == 'X')
                || (s.charAt(2) == 'X' && s.charAt(5) == 'X' && s.charAt(8) == 'X')
                || (s.charAt(0) == 'X' && s.charAt(4) == 'X' && s.charAt(8) == 'X')
                || (s.charAt(2) == 'X' && s.charAt(4) == 'X' && s.charAt(6) == 'X')) {
            return true;
        }
        return false;
    }

    static boolean winsO(String s) {
        if ((s.charAt(0) == 'O' && s.charAt(1) == 'O' && s.charAt(2) == 'O')
                || (s.charAt(3) == 'O' && s.charAt(4) == 'O' && s.charAt(5) == 'O')
                || (s.charAt(6) == 'O' && s.charAt(7) == 'O' && s.charAt(8) == 'O')
                || (s.charAt(0) == 'O' && s.charAt(3) == 'O' && s.charAt(6) == 'O')
                || (s.charAt(1) == 'O' && s.charAt(4) == 'O' && s.charAt(7) == 'O')
                || (s.charAt(2) == 'O' && s.charAt(5) == 'O' && s.charAt(8) == 'O')
                || (s.charAt(0) == 'O' && s.charAt(4) == 'O' && s.charAt(8) == 'O')
                || (s.charAt(2) == 'O' && s.charAt(4) == 'O' && s.charAt(6) == 'O')) {
            return true;
        }
        return false;
    }

    static boolean impossible(String s) {
        if ((s.charAt(0) == 'X' && s.charAt(1) == 'X' && s.charAt(2) == 'X'
                && s.charAt(3) == 'O' && s.charAt(4) == 'O' && s.charAt(5) == 'O')
                || (s.charAt(0) == 'X' && s.charAt(1) == 'X' && s.charAt(2) == 'X'
                && s.charAt(6) == 'O' && s.charAt(7) == 'O' && s.charAt(8) == 'O')
                || (s.charAt(3) == 'X' && s.charAt(4) == 'X' && s.charAt(5) == 'X'
                && s.charAt(0) == 'O' && s.charAt(1) == 'O' && s.charAt(2) == 'O')
                || (s.charAt(3) == 'X' && s.charAt(4) == 'X' && s.charAt(5) == 'X'
                && s.charAt(6) == 'O' && s.charAt(7) == 'O' && s.charAt(8) == 'O')
                || (s.charAt(6) == 'X' && s.charAt(7) == 'X' && s.charAt(8) == 'X'
                && s.charAt(0) == 'O' && s.charAt(1) == 'O' && s.charAt(2) == 'O')
                || (s.charAt(6) == 'X' && s.charAt(7) == 'X' && s.charAt(8) == 'X'
                && s.charAt(3) == 'O' && s.charAt(4) == 'O' && s.charAt(5) == 'O')
                || (s.charAt(0) == 'X' && s.charAt(3) == 'X' && s.charAt(6) == 'X'
                && s.charAt(1) == 'O' && s.charAt(4) == 'O' && s.charAt(7) == 'O')
                || (s.charAt(0) == 'X' && s.charAt(3) == 'X' && s.charAt(6) == 'X'
                && s.charAt(2) == 'O' && s.charAt(5) == 'O' && s.charAt(8) == 'O')
                || (s.charAt(1) == 'X' && s.charAt(4) == 'X' && s.charAt(7) == 'X'
                && s.charAt(0) == 'O' && s.charAt(3) == 'O' && s.charAt(6) == 'O')
                || (s.charAt(1) == 'X' && s.charAt(4) == 'X' && s.charAt(7) == 'X'
                && s.charAt(2) == 'O' && s.charAt(5) == 'O' && s.charAt(8) == 'O')
                || (s.charAt(2) == 'X' && s.charAt(5) == 'X' && s.charAt(8) == 'X'
                && s.charAt(0) == 'O' && s.charAt(3) == 'O' && s.charAt(6) == 'O')
                || (s.charAt(2) == 'X' && s.charAt(5) == 'X' && s.charAt(8) == 'X'
                && s.charAt(1) == 'O' && s.charAt(4) == 'O' && s.charAt(7) == 'O')) {
            return true;
        }
        return false;
    }

    static boolean impCount(String s) {
        int countO = 0;
        int countX = 0;
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) == 'O') {
                countO++;
            } else if (s.charAt(x) == 'X') {
                countX++;
            }
        }
        if (countO + 1 < countX || countX + 1 < countO) {
            return true;
        }
        return false;
    }

}
