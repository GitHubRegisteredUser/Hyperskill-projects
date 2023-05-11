package Hyperskill.SimpleTicTacToe;

import java.util.Scanner;
import java.util.InputMismatchException;

public class STTT5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] field = new char[3][3];
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = ' ';
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        int xs = 0;
        int os = 0;
        while (true) {
            try {
                int m = scanner.nextInt();
                int n = scanner.nextInt();
                if (m > 3 || n > 3 || m < 1 || n < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (field[m - 1][n - 1] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        if (xs == os) {
                            field[m - 1][n - 1] = 'X';
                            xs++;
                            printField(field);
                            if (winsX(field)) {
                                System.out.println("X wins");
                                break;
                            }
                        } else {
                            field[m - 1][n - 1] = 'O';
                            os++;
                            printField(field);
                            if (winsO(field)) {
                                System.out.println("O wins");
                                break;
                            }
                        }
                        if (os + xs == 9) {
                            System.out.println("Draw");
                            break;
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.next();
            }
        }
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

    static boolean winsX(char[][] array) {
        if ((array[0][0] == 'X' && array[0][1] == 'X' && array[0][2] == 'X')
                || (array[1][0] == 'X' && array[1][1] == 'X' && array[1][2] == 'X')
                || (array[2][0] == 'X' && array[2][1] == 'X' && array[2][2] == 'X')
                || (array[0][0] == 'X' && array[1][0] == 'X' && array[2][0] == 'X')
                || (array[0][1] == 'X' && array[1][1] == 'X' && array[2][1] == 'X')
                || (array[0][2] == 'X' && array[1][2] == 'X' && array[2][2] == 'X')
                || (array[0][0] == 'X' && array[1][1] == 'X' && array[2][2] == 'X')
                || (array[0][2] == 'X' && array[1][1] == 'X' && array[2][0] == 'X')) {
            return true;
        }
        return false;
    }

    static boolean winsO(char[][] array) {
        if ((array[0][0] == 'O' && array[0][1] == 'O' && array[0][2] == 'O')
                || (array[1][0] == 'O' && array[1][1] == 'O' && array[1][2] == 'O')
                || (array[2][0] == 'O' && array[2][1] == 'O' && array[2][2] == 'O')
                || (array[0][0] == 'O' && array[1][0] == 'O' && array[2][0] == 'O')
                || (array[0][1] == 'O' && array[1][1] == 'O' && array[2][1] == 'O')
                || (array[0][2] == 'O' && array[1][2] == 'O' && array[2][2] == 'O')
                || (array[0][0] == 'O' && array[1][1] == 'O' && array[2][2] == 'O')
                || (array[0][2] == 'O' && array[1][1] == 'O' && array[2][0] == 'O')) {
            return true;
        }
        return false;
    }

}
