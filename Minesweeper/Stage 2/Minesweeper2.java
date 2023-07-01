package Minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper2 {

    static char[][] mineField = createMineField();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        int mines = scanner.nextInt();
        setMines(mines);
        printMineField();
    }

    static char[][] createMineField() {
        char[][] array = new char[9][9];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = '.';
            }
        }
        return array;
    }

    static void printMineField() {
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                System.out.print(mineField[i][j]);
            }
            System.out.println();
        }
    }

    static void setMines(int m) {
        int count = 0;
        while (count < m) {
            Random random = new Random();
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (mineField[row][col] != 'X') {
                mineField[row][col] = 'X';
                count++;
            }
        }
    }

}
