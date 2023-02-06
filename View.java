import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class View {
    //Class to deal with printing to terminal and receiving input from user.

    Scanner in;

    Command command;

    View() {
        in = new Scanner(System.in);
    }

    public void welcomeMsg() {
        System.out.println("*** *** Welcome to Cascadia! *** ***");
        System.out.println("");
        System.out.println("This is a board game which can be played by 2-4 players.");
        System.out.println("The aim of the game is to have the most score by then end.");
        System.out.println("There are 85 habitat tiles, 25 of which are keystones, and 5 starter habitats.");
        System.out.println("There is also 100 wildlife tokens (20 Bear, 20 Elk, 20 Salmon, 20 Hawk, and 20 Fox).");
        System.out.println("To determine the score at the end of the game, one scoring card per wildlife is given.");
        System.out.println("A deck will be shown on the table, having 4 habitat tiles and 4 wildlife tokens.");
        System.out.println("If all 4 wildlife tokens are the same, they are re-drawn. If 3 of the wildlife tokens match,");
        System.out.println("may choose to re-draw those 3 tokens.");
        System.out.println("Each player will select a habitat/wildlife pair and add them to their habitat board per turn.");
        System.out.println("A wildlife can only be placed on a habitat if its respective symbol is shown.");
        System.out.println("If you place a wildlife on a keystone tile, you receive a nature token");
        System.out.println("These can be used to, take ANY one of the 4 habitat tiles and ANY one of the 4 wildlife tokens,");
        System.out.println("Wipe any number of wildlife tokens from the pool on the table. There is no limit to how many nature tokens you can spend per turn.");
        System.out.println("");
        System.out.println("Commands:");
        System.out.println("");
        System.out.println("");
    }

    //Gets desired number of players, returns it as an int
    public int getPlayerNum() {
        System.out.println("How many players are playing? (2-4): ");
        int num = 0;
        
        do {
            if(in.hasNextInt()) {
                int input = in.nextInt();
                if(input >= 2 && input <=4) {
                    num = input;
                } else {
                    System.out.println("Invalid amount of players given. Try again.");
                }
            } else {
                in.next();
                System.out.println("Invalid input given. Try again.");
            }
        }
        while (num == 0);
        return num;
    }

    //Creates and populates an arraylist of strings containing all player names
    public ArrayList<String> getplayerNames(int playNum) {
        ArrayList<String> players = new ArrayList<String>();


        do {
            System.out.println("Please enter a players name (Max 20 Characters): ");
            String input = in.next();
            if (input.length() > 21) {
                System.out.println("Please enter a name within 20 Characters. Try again.");
            } else {
                players.add(input);
            }
        } while (players.size() < playNum);
        return players;
    }


    public Command getUserInput() {
        boolean valid = false;
        do {
            System.out.println("Please enter command: ");
            String input = in.nextLine();
            if (Command.isValid(input)) {
                command = new Command(input);
                valid = true;
            } else {
                System.out.println("Invalid command given. Try again.");
            }
        } while (!valid);
        return command;
    }

    public void showPlaceTokenError() {
        System.out.println("Cant place wildlife token on tile.");
    }

    public void showGameOver(String playerWon) {
        System.out.println(playerWon + "won!!");
        System.out.println("Game over.");
    }

    public void showQuit() {
        System.out.println("Quitting game.");
    }

    public Command getCommand() {
        return command;
    }

    // random number generator to give a number between 2 and 4
    public static int randomizer(){
        Random rand = new Random();
        return rand.nextInt(3) + 2;
    }

    public void showFinalScore(ArrayList<String> players) {
        System.out.println("");
    }
}
