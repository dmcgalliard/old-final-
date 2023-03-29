import java.util.*;

//create game object
public class Game {
    Scanner in = new Scanner(System.in);

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // create function creates a new version of all objects in the game
    public void startGame() {
        Board board = new Board();
        Player p1 = new Player("temp");
        Game Game1 = new Game();

        askName(board, p1, Game1);

    }

    // Create home screen after user name is collected
    public void menue(Board b, Player p, Game g) {
        clear();
        System.out.println("Welcome to Minesweeper " + p.getName() + "!\n");

        System.out.println("Begin playing (p) ");
        System.out.println("update player (u) ");
        System.out.println("Read rules (r) ");

        System.out.println("What would you like to do? ");
        char i = in.next().charAt(0);

        if (i == 'p') {
            clear();
            b.printBoard(b, p, g);
        } else if (i == 'u') {
            clear();

            askName(b, p, g);
        } else if (i == 'r') {
            clear();
            rules(b, p, g);
        }
        // Exceptions for invalid input
        else {
            while (i != 'p' && i != 'u' && i != 'r') {
                System.out.println("Invalid input please try again: ");
                i = in.next().charAt(0);
            }
            if (i == 'p') {
                clear();
                b.printBoard(b, p, g);
            } else if (i == 'u') {
                clear();
                askName(b, p, g);
            } else if (i == 'r') {
                clear();
                rules(b, p, g);
            }

        }

    }

    // how to play the game
    public void rules(Board b, Player p, Game g) {
        clear();

        System.out.println("Hi! Thanks for playing Minesweeper");
        System.out.println("Before playing you need to know a few basic rules and commands.");
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Taking a turn: ");
        System.out.println();
        System.out.println(
                "- After starting the game you are presented with the current state of the board with letters a-l on the top of the board and 1-12 on the left side of the board and the prompt: Please enter a position between A1 and L12");
        System.out.println("- To enter a desired input the input must fit the following parameters: ");
        System.out.println();
        System.out.println(
                "   -the first chracter must be a letter in the range of a to l. The letter can be lower or uppercase");
        System.out.println("   -the rest of the input must be an integer from 1 - 12");
        System.out.println("   -Examples of valid inputs: A1, e12, g6");
        System.out.println("   -Examples of incorrect inputs: 2A, 3g, e13");
        System.out.println();
        System.out.println("What happens to this input?");
        System.out.println();
        System.out.println(
                "- After taking in valid input every square immediately around that postion is searched. If the square is empty(There are no bombs in immediate perimeter) then the process is repeated on each square in the inital positions input. When a postion does have at least one bomb in its perimeter then that position on the board is changed to represent the number of bombs in the perimeter. This process repeats until there are no more empty squares. If the initial input does have at least one bomb in its perimeter then the only update to the board is that position being changed to represent the number of bombs in its perimeter. After either of these sequences complete then you are prompted for another position.");
        System.out.println();
        System.out.println("How do I win or lose?");
        System.out.println();
        System.out.println(
                "- If the position you select is a bomb then the game ends and you are asked to either quit or start a fresh game.");
        System.out.println(
                "- If you have mangaed to uncover every single square except for the ones that are bombs then you are told that you have one and can either leave or start a fresh game.");
        System.out.println();
        System.out.println("You should have all you need to begin, Good luck!");
        System.out.println();
        System.out.println("-----------------------------------------");

        System.out.println("Enter q to return to the menue: ");
        char input = in.next().charAt(0);
        if (input == 'q') {
            clear();
            menue(b, p, g);
        } else {
            while (input != 'q') {
                System.out.println("Invalid input. please enter q to return to the menue: ");
                input = in.next().charAt(0);
            }
            clear();
            menue(b, p, g);
        }
    }

    // get the users name and then send them to the menue
    public void askName(Board b, Player p, Game g) {
        Scanner temp = new Scanner(System.in);
        System.out.println("Hi! Please enter new player name: ");
        p.name = temp.nextLine();

        System.out.println("New name is " + p.getName() + ". Enter c to continue: ");
        char input = temp.next().charAt(0);
        if (input == 'c') {
            clear();
            menue(b, p, g);
        } else {
            while (input != 'c') {
                System.out.println("Invlid input. Enter c to continue: ");
                input = temp.next().charAt(0);
            }
            clear();
            menue(b, p, g);
        }

        temp.close();

    }

}