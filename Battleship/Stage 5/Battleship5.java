package Hyperskill.Battleship;

import java.util.Scanner;

public class Battleship5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] field1 = createField();
        String[][] field2 = createField();
        String[][] hiddenField1 = createField();
        String[][] hiddenField2 = createField();

        Ship[] ship = {new Ship("Aircraft Carrier", 5), new Ship("Battleship", 4),
            new Ship("Submarine", 3), new Ship("Cruiser", 3), new Ship("Destroyer", 2)};
        System.out.println("Player 1, place your ships on the game field");
        printField(field1);
        for (int i = 0; i < ship.length; i++) {
            System.out.println("Enter the coordinates of the " + ship[i].getName()
                    + " (" + ship[i].getLength() + " cells):");
            while (true) {
                String a = scanner.next();
                String b = scanner.next();
                if (!check(field1, ship[i].getName(), ship[i].getLength(), a, b)) {
                    continue;
                }
                placeShip(field1, a, b);
                printField(field1);
                break;
            }
        }
        System.out.println("Press Enter and pass the move to another player\n"
                + "...");
        scanner.nextLine();
        scanner.nextLine();

        System.out.println("Player 2, place your ships on the game field");
        printField(field2);
        for (int i = 0; i < ship.length; i++) {
            System.out.println("Enter the coordinates of the " + ship[i].getName()
                    + " (" + ship[i].getLength() + " cells):");
            while (true) {
                String a = scanner.next();
                String b = scanner.next();
                if (!check(field2, ship[i].getName(), ship[i].getLength(), a, b)) {
                    continue;
                }
                placeShip(field2, a, b);
                printField(field2);
                break;
            }
        }
        System.out.println("Press Enter and pass the move to another player\n"
                + "...");
        scanner.nextLine();
        scanner.nextLine();

        int countHP1 = 0;
        int countHP2 = 0;
        while (true) {
            printField(hiddenField2);
            System.out.println("---------------------");
            printField(field1);
            System.out.println("Player 1, it's your turn:");
            String a = scanner.next();
            if (!shootCheck(a)) {
                continue;
            }
            countHP2 = shoot(field2, hiddenField2, a, countHP2);
            if (countHP2 == 17) {
                break;
            }
            System.out.println("Press Enter and pass the move to another player\n"
                    + "...");
            scanner.nextLine();
            scanner.nextLine();

            printField(hiddenField1);
            System.out.println("---------------------");
            printField(field2);
            System.out.println("Player 2, it's your turn:");
            String b = scanner.next();
            if (!shootCheck(b)) {
                continue;
            }
            countHP1 = shoot(field1, hiddenField1, b, countHP1);
            if (countHP1 == 17) {
                break;
            }
            System.out.println("Press Enter and pass the move to another player\n"
                    + "...");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    static String[][] createField() {
        String[][] field = new String[12][12];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (i == 0 && j == 0) {
                    field[i][j] = " ";
                } else if (i == 11 || j == 11) {
                    field[i][j] = "";
                } else if (i == 0) {
                    field[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    char c = (char) (64 + i);
                    field[i][j] = Character.toString(c);
                } else {
                    field[i][j] = "~";
                }
            }
        }
        return field;
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

    static int shoot(String[][] array, String[][] hiddenArray, String a, int c) {
        int counter = c;
        String s = "";
        a = replaceChar(a);
        int i = a.charAt(0) - 64;
        int j = a.charAt(1) - 47;

        if (array[i][j].equals("O")) {
            array[i][j] = "X";
            hiddenArray[i][j] = "X";
            counter++;
            if (counter == 17) {
                s = "You sank the last ship. You won. Congratulations!";
            } else if ((!array[i + 1][j].equals("O") && !array[i][j + 1].equals("O")
                    && !array[i - 1][j].equals("O") && !array[i][j - 1].equals("O"))) {
                s = "You sank a ship!";
            } else {
                s = "You hit a ship!";
            }
        } else if (array[i][j].equals("X")) {
            s = "You hit a ship!";
        } else {
            array[i][j] = "M";
            hiddenArray[i][j] = "M";
            s = "You missed!";
        }

        System.out.println(s);
        return counter;
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
