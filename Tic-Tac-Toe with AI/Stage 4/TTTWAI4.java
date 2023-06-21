package TicTacToeWithAI;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TTTWAI4 {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static String[] parameters;
    static int index;
    static char symbol;

    public static void main(String[] args) {
        while (true) {
            setParameters();
            char[][] field = createField();
            printField(field);
            gameLoop(field);
        }
    }

    static char[][] createField() {
        char[][] array = new char[3][3];
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

    static void setParameters() {
        while (true) {
            System.out.println("Input command: ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.exit(0);
            }
            parameters = input.split(" ");
            if (parameters.length != 3 || !parameters[0].equals("start") ||
                    !(parameters[1].equals("medium") || (parameters[1].equals("easy") ||
                            parameters[1].equals("user"))) || !(parameters[2].equals("medium") ||
                    (parameters[2].equals("easy") || parameters[2].equals("user")))) {
                System.out.println("Bad parameters!");
            } else {
                index = 1;
                symbol = 'X';
                break;
            }
        }
    }

    static void gameLoop(char[][] array) {
        int m;
        int n;

        while (true) {
            try {
                if (parameters[index].equals("user")) {
                    System.out.print("Enter the coordinates: ");
                    m = scanner.nextInt();
                    n = scanner.nextInt();
                } else if (parameters[index].equals("easy")) {
                    System.out.println("Making move level \"easy\"");
                    m = random.nextInt(3) + 1;
                    n = random.nextInt(3) + 1;
                } else {
                    m = mediumMove(array)[0];
                    n = mediumMove(array)[1];
                }
                if (m > 3 || n > 3 || m < 1 || n < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
                if (array[m - 1][n - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    array[m - 1][n - 1] = symbol;
                    printField(array);
                    if (winCheck(array)) {
                        System.out.println(symbol + " wins");
                        break;
                    }
                    if (drawCheck(array)) {
                        System.out.println("Draw");
                        break;
                    }
                    if (symbol == 'X') {
                        index = 2;
                        symbol = 'O';
                    } else {
                        index = 1;
                        symbol = 'X';
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.next();
            }
        }
    }

    static boolean winCheck(char[][] array) {
        if ((array[0][0] == symbol && array[1][1] == symbol && array[2][2] == symbol)
                || (array[0][2] == symbol && array[1][1] == symbol && array[2][0] == symbol)) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if ((array[i][0] == symbol && array[i][1] == symbol && array[i][2] == symbol) ||
                    (array[0][i] == symbol && array[1][i] == symbol && array[2][i] == symbol)) {
                return true;
            }
        }
        return false;
    }

    static boolean drawCheck(char[][] array) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    static int[] mediumMove(char[][] array) {
        if (array[0][0] == array[1][1] && array[0][0] != ' ' && array[2][2] == ' ') {
            return new int[]{3, 3};
        } else if (array[0][0] == array[2][2] && array[0][0] != ' ' && array[1][1] == ' ') {
            return new int[]{2, 2};
        } else if (array[1][1] == array[2][2] && array[1][1] != ' ' && array[0][0] == ' ') {
            return new int[]{1, 1};
        } else if (array[0][2] == array[1][1] && array[0][2] != ' ' && array[2][0] == ' ') {
            return new int[]{3, 1};
        } else if (array[0][2] == array[2][0] && array[0][2] != ' ' && array[1][1] == ' ') {
            return new int[]{2, 2};
        } else if (array[1][1] == array[2][0] && array[1][1] != ' ' && array[0][2] == ' ') {
            return new int[]{1, 3};
        }

        for (int i = 0; i < 3; i++) {
            if (array[i][0] == array[i][1] && array[i][0] != ' ' && array[i][2] == ' ') {
                return new int[]{i + 1, 3};
            } else if (array[i][0] == array[i][2] && array[i][0] != ' ' && array[i][1] == ' ') {
                return new int[]{i + 1, 2};
            } else if (array[i][1] == array[i][2] && array[i][1] != ' ' && array[i][0] == ' ') {
                return new int[]{i + 1, 1};
            } else if (array[0][i] == array[1][i] && array[0][i] != ' ' && array[2][i] == ' ') {
                return new int[]{3, i + 1};
            } else if (array[0][i] == array[2][i] && array[0][i] != ' ' && array[1][i] == ' ') {
                return new int[]{2, i + 1};
            } else if (array[1][i] == array[2][i] && array[1][i] != ' ' && array[0][i] == ' ') {
                return new int[]{1, i + 1};
            }
        }
        return new int[]{random.nextInt(3) + 1, random.nextInt(3) + 1};
    }

}
