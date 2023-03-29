import java.util.*;

class Player {
    public String name;

    Scanner in = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Clear terminal
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Turn method
    public void turn(Board b, Player p, Game g) {
        int xCoord = -1;
        int yCoord = -1;
        Boolean validInput = true;

        do {
            validInput = true;
            // Accept input here
            System.out.println("Please enter a position between A1 and L12");
            String user = in.nextLine();

            // Check 1: is the input empty, length 2 or 3
            if (!(user.length() > 1 && user.length() < 4)) {
                turn(b, p, g);
            }

            try {
                // Try to collect necessary inputs
                int letter = (int) user.charAt(0);
                String number = user.substring(1);
                yCoord = Integer.parseInt(number) - 1;

                // Make sure number is between 1 and 12
                if (!(yCoord >= 0 && yCoord <= 11)) {
                    turn(b, p, g);
                }

                // Check 2: attempt to save letter variable to first character
                if (letter == 21) {
                    validInput = false;
                } else if (letter == 76 || letter == 108) {
                    xCoord = 11;
                } else if (letter == 75 || letter == 107) {
                    xCoord = 10;
                } else if (letter > 64 && letter < 75) {
                    // convert uppercase char to corresponding number
                    xCoord = Character.getNumericValue(letter - 17);
                } else if (letter > 96 && letter < 107) {
                    // convert lowercase char to number
                    xCoord = Character.getNumericValue(letter - 49);
                } else {
                    turn(b, p, g);
                }

                // Make sure letter is between 1 and 12
                if (!(xCoord >= 0 && xCoord <= 11)) {
                    turn(b, p, g);
                }

                if (xCoord > 11 || xCoord < 0 || yCoord > 11 || yCoord < 0) {
                    turn(b, p, g);
                }

            } catch (Exception e) {
                turn(b, p, g);
            }

        } while (!validInput);

        if (b.board[yCoord][xCoord] != "⬛️") {
            System.out.println("You've already revealed this square.");
            turn(b, p, g);
        }

        // Once input valid, take the turn, update the board
        b.check(xCoord, yCoord, g);
        clear();
        b.printBoard(b, p, g);
    }

}
