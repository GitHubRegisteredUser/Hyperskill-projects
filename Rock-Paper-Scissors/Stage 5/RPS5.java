package Hyperskill.RockPaperScissors;

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class RPS5 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        File file = new File("rating.txt");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Player p1 = new Player(name);
        System.out.println("Hello, " + p1.getName());
        String options = scanner.nextLine();
        String[] arrayOfOptions = options.split(",");
        System.out.println("Okay, let's start");

        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNext()) {
                if (name.equals(scan.next())) {
                    p1.setRating(scan.nextInt());
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

        while (true) {
            String input = scanner.next();
            if (input.contains("!exit")) {
                System.out.println("Bye!");
                break;
            }

            if (options.equals("")) {
                if (!input.equals("rock") && !input.equals("paper")
                        && !input.equals("scissors") && !input.equals("!rating")) {
                    System.out.println("Invalid input");
                    continue;
                }
                int r = random.nextInt(3);

                switch (input) {
                    case "rock":
                        if (r == 0) {
                            System.out.println("There is a draw (rock)");
                            p1.plusRating(50);
                            break;
                        } else if (r == 1) {
                            System.out.println("Sorry, but the computer chose paper");
                            break;
                        } else {
                            System.out.println("Well done. The computer chose scissors and failed");
                            p1.plusRating(100);
                            break;
                        }
                    case "paper":
                        if (r == 0) {
                            System.out.println("Well done. The computer chose rock and failed");
                            p1.plusRating(100);
                            break;
                        } else if (r == 1) {
                            System.out.println("There is a draw (paper)");
                            p1.plusRating(50);
                            break;
                        } else {
                            System.out.println("Sorry, but the computer chose scissors");
                            break;
                        }
                    case "scissors":
                        if (r == 0) {
                            System.out.println("Sorry, but the computer chose rock");
                            break;
                        } else if (r == 1) {
                            System.out.println("Well done. The computer chose paper and failed");
                            p1.plusRating(100);
                            break;
                        } else {
                            System.out.println("There is a draw (scissors)");
                            p1.plusRating(50);
                            break;
                        }
                    case "!rating":
                        System.out.println("Your rating: " + p1.getRating());
                        break;

                }
            } else {
                if (!options.contains(input) && !input.equals("!rating")) {
                    System.out.println("Invalid input");
                    continue;
                }
                if (input.equals("!rating")) {
                    System.out.println("Your rating: " + p1.getRating());
                    continue;
                }

                int r = random.nextInt(arrayOfOptions.length);
                int i = -1;
                for (int j = 0; j < arrayOfOptions.length; j++) {
                    if (input.equals(arrayOfOptions[j])) {
                        i = j;
                        break;
                    }
                }
                if (r == i) {
                    System.out.println("There is a draw (" + arrayOfOptions[i] + ")");
                    p1.plusRating(50);
                } else if (i < arrayOfOptions.length / 2) {
                    if (r > i && r <= i + arrayOfOptions.length / 2) {
                        System.out.println("Sorry, but the computer chose " + arrayOfOptions[r]);
                    } else {
                        System.out.println("Well done. The computer chose " + arrayOfOptions[r] + " and failed");
                        p1.plusRating(100);
                    }
                } else {
                    if (r < i && r >= i - arrayOfOptions.length / 2) {
                        System.out.println("Well done. The computer chose " + arrayOfOptions[r] + " and failed");
                        p1.plusRating(100);
                    } else {
                        System.out.println("Sorry, but the computer chose " + arrayOfOptions[r]);
                    }
                }
            }
        }

    }

}

class Player {

    private String name;
    private int rating = 100;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void plusRating(int rating) {
        this.rating += rating;
    }

}
