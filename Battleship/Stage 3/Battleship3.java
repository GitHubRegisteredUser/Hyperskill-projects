package Hyperskill.Battleship;

import java.util.Scanner;

public class Battleship3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] field = new String[11][11];
        String[][] hiddenField = new String[11][11];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (i == 0 && j == 0) {
                    field[i][j] = " ";
                    hiddenField[i][j] = " ";
                } else if (i == 0) {
                    field[i][j] = Integer.toString(j);
                    hiddenField[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    char c = (char) (64 + i);
                    field[i][j] = Character.toString(c);
                    hiddenField[i][j] = Character.toString(c);
                } else {
                    field[i][j] = "~";
                    hiddenField[i][j] = "~";
                }
                System.out.print(field[i][j] + " ");
            }
            System.out.println("");
        }

        Ship[] ship = {new Ship("Aircraft Carrier", 5), new Ship("Battleship", 4),
            new Ship("Submarine", 3), new Ship("Cruiser", 3), new Ship("Destroyer", 2)};
        for (int i = 0; i < ship.length; i++) {
            System.out.println("Enter the coordinates of the " + ship[i].getName()
                    + " (" + ship[i].getLength() + " cells):");
            while (true) {
                String a = scanner.next();
                String b = scanner.next();
                if (!check(field, ship[i].getName(), ship[i].getLength(), a, b)) {
                    continue;
                }
                placeShip(field, a, b);
                printField(field);
                break;
            }
        }

        System.out.println("The game starts!");
        printField(hiddenField);
        System.out.println("Take a shot!");
        while (true) {
            String a = scanner.next();
            if (!shootCheck(a)) {
                continue;
            }
            shoot(field, hiddenField, a);
            break;
        }
    }

    static void printField(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }

    static String replaceChar(String x) {
        if (x.contains("10")) {
            x = x.replace("10", "9");
        } else {
            x = x.replace(x.charAt(1), (char) (x.charAt(1) - 1));
        }
        return x;
    }

    static void placeShip(String[][] array, String a, String b) {
        a = replaceChar(a);
        b = replaceChar(b);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if ((j >= a.charAt(1) - 47 && j <= b.charAt(1) - 47
                        && i >= a.charAt(0) - 64 && i <= b.charAt(0) - 64)
                        || (j <= a.charAt(1) - 47 && j >= b.charAt(1) - 47
                        && i <= a.charAt(0) - 64 && i >= b.charAt(0) - 64)) {
                    array[i][j] = "O";
                }
            }
        }
    }

    static boolean check(String[][] array, String name, int l, String a, String b) {
        a = replaceChar(a);
        b = replaceChar(b);

        if (a.charAt(0) != b.charAt(0) && a.charAt(1) != b.charAt(1)) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }

        if (Math.abs(a.charAt(0) - b.charAt(0)) != l - 1
                && Math.abs(a.charAt(1) - b.charAt(1)) != l - 1) {
            System.out.println("Error! Wrong length of the " + name + "! Try again:");
            return false;
        }

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array[i].length - 1; j++) {
                if ((j >= a.charAt(1) - 47 && j <= b.charAt(1) - 47
                        && i >= a.charAt(0) - 64 && i <= b.charAt(0) - 64)
                        || (j <= a.charAt(1) - 47 && j >= b.charAt(1) - 47
                        && i <= a.charAt(0) - 64 && i >= b.charAt(0) - 64)) {
                    if (array[i + 1][j].equals("O") || array[i][j + 1].equals("O")
                            || array[i - 1][j].equals("O") || array[i][j - 1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean shootCheck(String a) {
        if ((a.length() == 3 && (a.charAt(2) != 48
                || a.charAt(1) != 49 || a.charAt(0) > 74))
                || (a.length() == 2 && a.charAt(0) > 74)) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }
        return true;
    }

    static void shoot(String[][] array, String[][] hiddenArray, String a) {
        String s = "";
        a = replaceChar(a);
        Outer:
        for (int i = 0; i < array.length; i++) {
            Inner:
            for (int j = 0; j < array[i].length; j++) {
                if (j == a.charAt(1) - 47 && i == a.charAt(0) - 64) {
                    if (array[i][j].equals("O")) {
                        array[i][j] = "X";
                        hiddenArray[i][j] = "X";
                        s = "You hit a ship!";
                        break Outer;
                    } else {
                        array[i][j] = "M";
                        hiddenArray[i][j] = "M";
                        s = "You missed!";
                        break Outer;
                    }
                }
            }
        }
        printField(hiddenArray);
        System.out.println(s);
        printField(array);
    }

}

class Ship {

    private String name;
    private int length;

    Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

}
