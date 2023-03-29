import java.util.*;

public class Board {

    // make a board for the player as well as a duplicate array that has the bombs
    // randomly dispersed across it
    public String[][] board = new String[12][12];
    public String[][] hideBomb = new String[12][12];
    Scanner in = new Scanner(System.in);

    public Board() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                board[i][j] = "⬛️";
            }
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                hideBomb[i][j] = "⬛️";
            }
        }
        for (int i = 0; i < 22; i++) {
            int r1 = (int) (Math.random() * 11 + 0);
            int r2 = (int) (Math.random() * 11 + 0);
            if (hideBomb[r1][r2] != "💣") {
                hideBomb[r1][r2] = "💣";
            } else {
                while (hideBomb[r1][r2] == "💣") {
                    r1 = (int) (Math.random() * 11 + 0);
                    r2 = (int) (Math.random() * 11 + 0);
                }
                hideBomb[r1][r2] = "💣";
            }
        }

    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // method to count and return the number of bombs in the perimeter of a position
    public int bc(int x, int y) {
        int bCount = 0;

        if (hideBomb[y][x] != "💣") {
            try {
                if (hideBomb[y - 1][x - 1] == "💣") {
                    bCount += 1;
                }
            } catch (Exception e) {

            }

            try {
                if (hideBomb[y - 1][x] == "💣") {
                    bCount += 1;
                }
            } catch (Exception e) {

            }

            try {
                if (hideBomb[y - 1][x + 1] == "💣") {
                    bCount += 1;
                }
            } catch (Exception e) {

            }

            try {
                if (hideBomb[y][x - 1] == "💣") {
                    bCount += 1;
                }
            } catch (Exception e) {

            }
            try {
                if (hideBomb[y][x + 1] == "💣") {
                    bCount += 1;
                }
            } catch (Exception e) {

            }

            try {

                if (hideBomb[y + 1][x - 1] == "💣") {
                    bCount += 1;
                }
            } catch (Exception e) {

            }

            try {
                if (hideBomb[y + 1][x] == "💣") {
                    bCount += 1;
                }
            } catch (Exception e) {

            }

            try {
                if (hideBomb[y + 1][x + 1] == "💣") {
                    bCount += 1;
                }
            } catch (Exception e) {

            }
        }

        return bCount;

    }

    // method to dictate what happens when an emtpy square or when it isn't
    public void check(int x, int y, Game g) {
        // lose condition
        if (hideBomb[y][x] == "💣") {
            clear();
            System.out.println(
                    "The square you have selected was a bomb. You have lost the game. To start a new one enter n. If you wish to stop playing enter q: ");
            char input = in.next().charAt(0);
            if (input == 'n') {
                clear();
                g.startGame();
            } else if (input == 'q') {
                System.out.println("Thanks for playing!");
                System.exit(0);
            } else if (input != 'n' || input != 'q') {
                while (input != 'n' && input != 'q') {
                    System.out.println("Invalid input please try again: ");
                    input = in.next().charAt(0);
                }
                if (input == 'n') {
                    clear();
                    g.startGame();
                } else if (input == 'q') {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }

            }
            // conditions if the square isn't a bomb
        } else {

            int bc = bc(x, y);
            // only reveal the entered position of the users input if the square is not
            // empty
            if (bc >= 1) {
                revealCheck(x, y);
            }
            // call the check function on itself for the postions on top, bottom, to the
            // right, on the top, and the bottom of the empty square which repeats until
            // there are no more empty squares to check
            if (bc == 0) {
                revealCheck(x, y);
                try {
                    if (board[y][x + 1] == "⬛️") {
                        check(x + 1, y, g);
                    }
                } catch (Exception e) {

                }
                try {
                    if (board[y + 1][x] == "⬛️") {
                        check(x, y + 1, g);
                    }
                } catch (Exception e) {

                }
                try {
                    if (board[y][x - 1] == "⬛️") {
                        check(x - 1, y, g);
                    }
                } catch (Exception e) {

                }
                try {
                    if (board[y - 1][x] == "⬛️") {
                        check(x, y - 1, g);
                    }
                } catch (Exception e) {

                }

            }
        }

    }

    // method for changing the appearence of the postions on the board so that the
    // users board is up to date when printBoard runs again
    public void reveal(int x, int y) {
        int bc = bc(x, y);
        if (bc == 0) {
            board[y][x] = "🔲";
        }
        if (bc == 1) {
            board[y][x] = "1️⃣ ";
        }
        if (bc == 2) {
            board[y][x] = "2️⃣ ";
        }
        if (bc == 3) {
            board[y][x] = "️3️⃣ ";
        }
        if (bc == 4) {
            board[y][x] = "4️⃣ ";
        }
        if (bc == 5) {
            board[y][x] = "5️⃣ ";
        }
        if (bc == 6) {
            board[y][x] = "6️⃣ ";
        }
        if (bc == 7) {
            board[y][x] = "7️⃣ ";
        }
        if (bc == 8) {
            board[y][x] = "8️⃣ ";
        }

    }

    // method to make sure a bomb isnt passed into reveal
    public void revealCheck(int x, int y) {
        if (hideBomb[y][x] == "💣") {
            board[y][x] = "💣";
        } else {
            reveal(x, y);
        }

    }

    // print the most current version of the board and check if the player has won
    // yet
    public void printBoard(Board b, Player p, Game g) {
        // prints the board
        clear();
        System.out.println(
                "       A       B       C       D       E       F       G       H       I       J       K       L");
        for (int i = 0; i < 12; i++) {
            if (i + 1 <= 9) {
                System.out.print(i + 1 + "   ");
            } else if (i + 1 > 9) {
                System.out.print(i + 1 + "  ");
            }
            for (int j = -1; j < 11; j++) {
                if ((i + 1) <= 9) {
                    System.out.print("   " + board[i][j + 1] + "   ");
                } else if ((i + 1) > 9) {
                    System.out.print("   " + board[i][j + 1] + "   ");
                }

            }
            System.out.println();
            System.out.println();
        }

        // check if the user has won. If they have, the turn function isn't run again.
        // If they havn't won yet, turn is run again.
        int checkWinCount = 0;
        System.out.println();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (board[i][j] == "⬛️") {
                    checkWinCount += 1;
                }
            }
        }
        if (checkWinCount > 22) {
            p.turn(b, p, g);
        } else if (checkWinCount <= 22) {
            System.out.println("Congrats! You have won the game! enter c to restart the game or q to quit! ");

            char input = in.next().charAt(0);
            if (input == 'c') {
                clear();
                g.startGame();
            } else if (input == 'q') {
                System.out.println("Thanks for playing!");
                System.exit(0);
            } else if (input != 'c' || input != 'q') {
                while (input != 'c' && input != 'q') {
                    System.out.println("Invalid input please try again: ");
                    input = in.next().charAt(0);
                }
                if (input == 'c') {
                    clear();
                    g.startGame();
                } else if (input == 'q') {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }

            }

        }

    }

}