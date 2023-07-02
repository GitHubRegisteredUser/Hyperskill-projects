package Minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper3 {

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
                if (checkMines(i, j) > 0) {
                    mineField[i][j] = (char) (checkMines(i, j) + 48);
                }
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

    static int checkMines(int row, int col) {
        if (mineField[row][col] == 'X') {
            return -1;
        }

        int count = 0;
        int up = 1;
        int down = 1;
        int left = 1;
        int right = 1;

        if (row == 0) {
            up = 0;
        }
        if (row == mineField.length - 1) {
            down = 0;
        }
        if (col == 0) {
            left = 0;
        }
        if (col == mineField.length - 1) {
            right = 0;
        }

        for (int i = row - up; i <= row + down; i++) {
            for (int j = col - left; j <= col + right; j++) {
                if (mineField[i][j] == 'X') {
                    count++;
                }
            }
        }

        return count;
    }

}
