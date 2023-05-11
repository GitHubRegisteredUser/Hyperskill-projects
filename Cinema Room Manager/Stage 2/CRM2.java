package Hyperskill.CinemaRoomManager;

import java.util.Scanner;

public class CRM2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row");
        int seats = scanner.nextInt();
        int tSeats = rows * seats;
        int front = rows / 2;
        int back = rows - front;
        int tIncome;
        if (tSeats <= 60) {
            tIncome = 10 * tSeats;
        } else {
            tIncome = 10 * front * seats + 8 * back * seats;
        }
        System.out.println("Total income:");
        System.out.println("$" + tIncome);
    }

}
