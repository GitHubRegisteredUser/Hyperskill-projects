package Minesweeper;

public class Minesweeper1 {

    static char[][] mineField = createMineField();

    public static void main(String[] args) {
        mineField[0][8] = 'X';
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                if (i == j) {
                    mineField[i][j] = 'X';
                }
            }
        }
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

}
