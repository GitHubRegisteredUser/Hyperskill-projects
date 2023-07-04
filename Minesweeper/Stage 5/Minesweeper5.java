package Minesweeper;

import java.util.*;

public class Minesweeper5 {

    static Cell[][] mineField = createMineField();
    static int mines = 0;
    static int markedMines = 0;
    static List<Cell> list = new ArrayList<>();
    static Stack<Cell> stack = new Stack<>();

    public static void main(String[] args) {
        gameLoop();
    }

    static Cell[][] createMineField() {
        Cell[][] array = new Cell[9][9];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = new Cell();
                array[i][j].row = i;
                array[i][j].col = j;
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
                if (mineField[i][j].isOpened) {
                    if (mineField[i][j].isMine) {
                        System.out.print('X');
                    } else {
                        if (checkMines(i, j) == 0) {
                            System.out.print('/');
                        } else {
                            System.out.print(checkMines(i, j));
                        }
                    }
                } else if (mineField[i][j].isMarked) {
                    System.out.print('*');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println("|");
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

    static void firstTurn(int row, int col) {
        if (mineField[row][col].isMine) {
            for (int i = 0; i < (9 * 9); i++) {
                int newRow = i % mineField.length;
                int newCol = i / mineField.length;
                if (newRow != row || newCol != col) {
                    if (!mineField[newRow][newCol].isMine) {
                        mineField[newRow][newCol].isMine = true;
                        mineField[row][col].isMine = false;
                        list.remove(mineField[row][col]);
                        list.add(mineField[newRow][newCol]);
                        break;
                    }
                }
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

    static void markCell(Cell cell) {
        if (!cell.isOpened) {
            if (!cell.isMarked) {
                cell.isMarked = true;
                if (cell.isMine) {
                    markedMines++;
                }
            } else {
                cell.isMarked = false;
                if (cell.isMine) {
                    markedMines--;
                }
            }
        } else {
            System.out.println("There is a number here!");
        }
    }

    static void openCell(int row, int col) {
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
                if (!mineField[i][j].isOpened) {
                    if (checkMines(i, j) == 0) {
                        mineField[i][j].isOpened = true;
                        stack.push(mineField[i][j]);
                    } else if (checkMines(i, j) > 0) {
                        mineField[i][j].isOpened = true;
                    }
                }
            }
        }
    }

    static void openArea(int row, int col) {
        if (checkMines(row, col) > 0) {
            mineField[row][col].isOpened = true;
        } else {
            mineField[row][col].isOpened = true;
            openCell(row, col);

            while (!stack.empty()) {
                Cell nextCell = stack.pop();
                openCell(nextCell.row, nextCell.col);
            }
        }
    }

    static boolean isAllMinesMarked() {
        return markedMines == mines;
    }

    static boolean isAllSafeCellsOpened() {
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField.length; j++) {
                if (!mineField[i][j].isOpened && !mineField[i][j].isMine) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkExplosion(Cell cell) {
        if (cell.isMine) {
            openAllCells();
            return true;
        } else {
            return false;
        }
    }

    static void openAllCells() {
        for (Cell cell : list) {
            cell.isOpened = true;
        }
    }

    static void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        setMines(scanner.nextInt());
        printMineField();
        boolean isFirstTurn = true;
        boolean isGameOver;

        while (!isAllMinesMarked() || !isAllSafeCellsOpened()) {
            System.out.println("Set/unset mine marks or claim a cell as free: ");
            int y = Integer.parseInt(scanner.next()) - 1;
            int x = Integer.parseInt(scanner.next()) - 1;
            String s = scanner.next();

            if (s.equals("free")) {
                if (isFirstTurn) {
                    firstTurn(x, y);
                    isFirstTurn = false;
                }
                isGameOver = checkExplosion(mineField[x][y]);
                if (isGameOver) {
                    printMineField();
                    System.out.println("You stepped on a mine and failed!");
                    break;
                } else {
                    openArea(x, y);
                }
            } else {
                markCell(mineField[x][y]);
            }
            printMineField();
        }

        System.out.println("Congratulations! You found all mines!");
    }
}

class Cell {

    boolean isMine = false;
    boolean isMarked = false;
    boolean isOpened = false;
    int row;
    int col;

}
