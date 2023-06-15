package TicTacToeWithAI;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TTTWAI3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String p = "";
        String[] parameters;

        while (true) {
            while (true) {
                System.out.println("Input command: ");
                String input = scanner.nextLine();
                if (input.equals("exit")) {
                    System.exit(0);
                }
                parameters = input.split(" ");
                if (!parameters[0].equals("start") || parameters.length != 3 ||
                        !(parameters[1].equals("easy") || parameters[1].equals("user")) ||
                        !(parameters[2].equals("easy") || parameters[2].equals("user"))) {
                    System.out.println("Bad parameters!");
                } else {
                    p = parameters[1];
                    break;
                }
            }

            char[][] field = createField();
            printField(field);
            int m;
            int n;
            int xs = 0;
            int os = 0;

            while (true) {
                try {
                    if (p.equals("user")) {
                        System.out.print("Enter the coordinates: ");
                        m = scanner.nextInt();
                        n = scanner.nextInt();
                    } else {
                        System.out.println("Making move level \"easy\"");
                        m = random.nextInt(3) + 1;
                        n = random.nextInt(3) + 1;
                    }
                    if (m > 3 || n > 3 || m < 1 || n < 1) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else {
                        if (field[m - 1][n - 1] != ' ') {
                            System.out.println("This cell is occupied! Choose another one!");
                        } else {
                            if (xs == os) {
                                field[m - 1][n - 1] = 'X';
                                xs++;
                                p = parameters[2];
                                printField(field);
                                if (winsX(field)) {
                                    System.out.println("X wins");
                                    break;
                                }
                            } else {
                                field[m - 1][n - 1] = 'O';
                                os++;
                                p = parameters[1];
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
    }

    static char[][] createField() {
        char[][] array = new char[3][3];
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = ' ';
            }
        }
        return array;
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
