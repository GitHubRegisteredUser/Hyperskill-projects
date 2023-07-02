package Minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper4 {

    static Cell[][] mineField = createMineField();
    static int mines = 0;
    static int markedMines = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        setMines(scanner.nextInt());
        printMineField();
        gameLoop();
    }

    static Cell[][] createMineField() {
        Cell[][] array = new Cell[9][9];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = new Cell();
            }
        }
        return array;
    }

    static void printMineField() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < mineField.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < mineField[i].length; j++) {
                if (mineField[i][j].isMarked) {
                    System.out.print("*");
                } else {
                    if (checkMines(i, j) > 0) {
                        System.out.print(checkMines(i, j));
                    } else {
                        System.out.print(".");
                    }
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }

    static void setMines(int m) {
        while (mines < m) {
            Random random = new Random();
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (!mineField[row][col].isMine) {
                mineField[row][col].isMine = true;
                mines++;
            }
        }
    }

    static int checkMines(int row, int col) {
        if (mineField[row][col].isMine) {
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
                if (mineField[i][j].isMine) {
                    count++;
                }
            }
        }

        return count;
    }

    static void markCell(int y, int x) {
        if (checkMines(x - 1, y - 1) <= 0) {
            if (!mineField[x - 1][y - 1].isMarked) {
                mineField[x - 1][y - 1].isMarked = true;
                if (mineField[x - 1][y - 1].isMine) {
                    markedMines++;
                }
            } else {
                mineField[x - 1][y - 1].isMarked = false;
                if (mineField[x - 1][y - 1].isMine) {
                    markedMines--;
                }
            }
            printMineField();
        } else {
            System.out.println("There is a number here!");
        }
    }

    static void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        while (markedMines < mines) {
            System.out.println("Set/delete mines marks (x and y coordinates): ");
            markCell(scanner.nextInt(), scanner.nextInt());
        }
        System.out.println("Congratulations! You found all mines!");
    }

}

class Cell {

    boolean isMine = false;
    boolean isMarked = false;

}
