package Hyperskill.CinemaRoomManager;

import java.util.Scanner;

public class CRM4 {

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
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
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
        System.out.println("Enter a row number:");
        int rNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int sNumber = scanner.nextInt();
        int rows = array.length;
        int seats = array[0].length;
        int tSeats = rows * seats;
        int front = rows / 2;
        int tPrice;
        if (tSeats <= 60) {
            tPrice = 10;
        } else {
            if (rNumber < front) {
                tPrice = 10;
            } else {
                tPrice = 8;
            }
        }
        System.out.println("Ticket price: $" + tPrice);
        array[rNumber][sNumber] = "B";
    }

}
