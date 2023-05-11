package Hyperskill.CinemaRoomManager;

public class CRM1 {

    public static void main(String[] args) {
        int rows = 7;
        int seats = 8;
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
    }

}
