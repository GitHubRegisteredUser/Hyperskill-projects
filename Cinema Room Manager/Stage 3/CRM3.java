package Hyperskill.CinemaRoomManager;

import java.util.Scanner;

public class CRM3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row");
        int seats = scanner.nextInt();
        System.out.println("Cinema:");
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
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println("Enter a row number:");
        int rNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int sNumber = scanner.nextInt();
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

        System.out.println("Cinema:");
        cinema[rNumber][sNumber] = "B";
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
