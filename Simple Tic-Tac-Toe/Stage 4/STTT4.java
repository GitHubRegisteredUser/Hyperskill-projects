package Hyperskill.SimpleTicTacToe;

import java.util.Scanner;
import java.util.InputMismatchException;

public class STTT4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char[][] field = new char[3][3];
        int counter = 0;
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = input.charAt(counter++);
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        while (true) {
            try {
                int m = scanner.nextInt();
                int n = scanner.nextInt();
                if (m > 3 || n > 3 || m < 1 || n < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (field[m - 1][n - 1] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        field[m - 1][n - 1] = 'X';
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.next();
            }
        }
        printField(field);
    }

    static void printField(char[][] array) {
        System.out.println("---------");
        for (int i = 0; i < array.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

}
