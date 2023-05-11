package Hyperskill.CinemaRoomManager;

import java.util.Scanner;

public class CRM5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row");
        int seats = scanner.nextInt();
        String[][] cinema = new String[rows + 1][seats + 1];
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = " ";
                } else if (i == 0) {
                    cinema[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    cinema[i][j] = Integer.toString(i);
                } else {
                    cinema[i][j] = "S";
                }
            }
        }

        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 3:
                    showStats(cinema);
                    break;
                case 0:
                    return;
            }
        }
    }

    static void showSeats(String[][] array) {
        System.out.println("Cinema:");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }

    static void buyTicket(String[][] array) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter a row number:");
                int rNumber = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int sNumber = scanner.nextInt();
                if (array[rNumber][sNumber].equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    int rows = array.length - 1;
                    int seats = array[0].length - 1;
                    int tSeats = rows * seats;
                    int front = rows / 2;
                    int tPrice;
                    if (tSeats <= 60) {
                        tPrice = 10;
                    } else {
                        if (rNumber <= front) {
                            tPrice = 10;
                        } else {
                            tPrice = 8;
                        }
                    }
                    System.out.println("Ticket price: $" + tPrice);
                    array[rNumber][sNumber] = "B";
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        }
    }

    static void showStats(String[][] array) {
        int rows = array.length - 1;
        int seats = array[0].length - 1;
        int tSeats = rows * seats;
        int front = rows / 2;
        int back = rows - front;
        int pTickets = 0;
        int cIncome = 0;
        int tIncome = 0;
        if (tSeats <= 60) {
            tIncome = 10 * tSeats;
        } else {
            tIncome = 10 * front * seats + 8 * back * seats;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].equals("B")) {
                    if ((tSeats <= 60) || i <= (array.length - 1) / 2) {
                        pTickets++;
                        cIncome += 10;
                    } else {
                        pTickets++;
                        cIncome += 8;
                    }
                }
            }
        }
        double percentage = (double) pTickets / tSeats * 100;
        System.out.println("Number of purchased tickets: " + pTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + cIncome);
        System.out.println("Total income: $" + tIncome);
    }

}
