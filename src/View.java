import java.util.ArrayList;
import java.util.Scanner;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

public class View {
    //Class to deal with printing to terminal and receiving input from user.

    private final Scanner in;

    private Command command;
    private final String largeTab = "            ";

    View() {
        in = new Scanner(System.in);
    }

    //Displays opening welcome message
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
    }

    //Displays all commands
    public void displayCommands() {
        System.out.println("Commands:");
        System.out.println("Enter 'N' to use a nature token.");
        System.out.println("Enter 'C' to display commands again.");
        System.out.println("Enter 'S'[1-4] to select a tile and wildlife pair from the table.");
        System.out.println("Enter 'R' to rotate a selected tile after selection");
        System.out.println("Enter 'P'[0-99] to place the selected tile or wildlife on the board");
    }

    public void displayDevCommands() {
        System.out.println("In dev mode, a users turn does not end upon placing they're tokens.");
        System.out.println("Dev Commands:");
        System.out.println("Enter 'DEV_MODE' to exit dev mode");
        System.out.println("Enter 'N_TURN' to go to next player");
    }

    //Clears the view with new lines and adds a line. Used when at a new game state
    public void clearView() {
        skipLines();
        skipLines();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void skipLines() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    //Gets desired number of players, returns it as an int
    public int getPlayerNum() {
        System.out.println("How many players are playing? (2-4): ");
        int num = 0;
        do {
            if (in.hasNextInt()) {
                int input = in.nextInt();
                in.nextLine();
                if (input >= 2 && input <= 4) {
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
            String input = in.nextLine();
            input = input.trim();
            if (input.length() > 21 || input.isBlank()) {
                System.out.println("Please enter a non-empty name within 20 Characters. Try again.");
            } else {
                players.add(input);
            }
        } while (players.size() < playNum);
        return players;
    }

    public void displayScreen(User user, Board board, Table table, Scoring score) {
        score.generateScore(user);
        skipLines();
        printTable(table);
        System.out.println("Current Players Board: " + user.getName());
        System.out.println("Current Players Score: " + user.getScore());
        System.out.println("Players Nature Tokens: " + user.getNatureTokens());
        printBoard(board);
        if(table.hadSelectedTile() || table.hadSelectedWildlife()) {
            displaySelected(table);
        }
    }

    //Displays full board
    public void printBoard(Board board) {
        String out = "";
        for (int row = 0; row < board.getBoardLength(); row++) {
            if (!board.isRowNull(row)) {
                for (int tileRow = 0; tileRow < 4; tileRow++) {
                    out = largeTab + largeTab + largeTab + largeTab;
                    if (row % 2 == 1) {
                        out += "      ";
                    }
                    for (int i = 0; i < board.getBoardWidth(); i++) {
                        if(i == board.getBoardWidth() - 1) {
                            if(!board.isColumnBlank(i)) {
                                out += board.getTile(row, i).toString(tileRow);
                            }
                        } else if(i == 0) {
                            if(!board.isColumnBlank(i) || !board.isColumnBlank(i + 1)) {
                                out += board.getTile(row, i).toString(tileRow);
                            }
                        } else {
                            if(!board.isColumnBlank(i) || !board.isColumnBlank(i + 1) || !board.isColumnBlank(i - 1)) {
                                out += board.getTile(row, i).toString(tileRow);
                            }
                        }
                    }
                    System.out.println(out);
                }
            }
        }
    }

    //Displays table, being the 4 shown tiles and the 4 shown wildlife
    public void printTable(Table table) {
        StringBuilder out = new StringBuilder();
        out.append(largeTab + largeTab + largeTab);
        out.append("**                         Available Tiles and Wildlife                           **\n");


        for (int i = 0; i < 4; i++) {
            out.append(largeTab + largeTab + largeTab);
            for (int j = 0; j < 4; j++) {
                out.append(table.getTile(j).toString(i));
                out.append(largeTab);
            }
            out.append("\n");
        }
        out.append("\n");
        out.append(largeTab + largeTab + largeTab);
        for (int i = 0; i < 4; i++) {
            out.append("       ");
            out.append(table.getWildlife(i));
            out.append(largeTab);
        }
        out.append("\n");
        out.append(largeTab + largeTab + largeTab);
        out.append("**       [1]                  [2]                  [3]                 [4]         **");
        System.out.println(out);
    }

    public void displaySelected(Table table) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(largeTab + largeTab + largeTab);
            if (table.hadSelectedTile()) {
                sb.append(table.getSelectedTile().toString(i));
            }
            if (i == 3 && table.hadSelectedWildlife()) {
                sb.append(largeTab + largeTab);
                sb.append(table.getSelectedWildlife());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    //method to receive user input for commands
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

    public boolean getUserYorN() {
        boolean valid = false;
        boolean inputBool = false;
        System.out.println("Please enter Y (yes) or N (no): ");
        do {
            String input = in.nextLine();
            if (input.trim().equalsIgnoreCase("Y")) {
                valid = true;
                inputBool = true;
            } else if (input.trim().equalsIgnoreCase("N")) {
                valid = true;
            } else {
                System.out.println("Invalid input entered. Please Enter Y (yes) or N (no).");
            }
        } while (!valid);
        return inputBool;
    }

    //Prompts the user to enter a number between the 2 bounds given, inclusive
    public int getUserint(int lowBound, int highBound) {
        boolean valid = false;
        int inputInt = -1;
        if(lowBound >= highBound) {
            int tmp = highBound;
            highBound = lowBound;
            lowBound = tmp;
        }
        System.out.println("Please enter a number between " + lowBound + " and " + highBound + ", inclusive: ");
        do {

            String input = in.nextLine();
            try {
                inputInt = Integer.parseInt(input);
                if(inputInt >= lowBound && inputInt <= highBound) {
                    valid = true;
                } else {
                    System.out.println("Invalid input entered. Please enter a number between "
                            + lowBound + " and " + highBound + ", inclusive.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input entered. Please enter a number.");
            }
        } while(!valid);
        return inputInt;
    }

    //Prompts the user to enter a number list, all between the 2 bounds given, inclusive
    public int[] getUserintArray(int lowBound, int highBound) {
        boolean valid = false;
        ArrayList<Integer> intArrayList = new ArrayList<>();
        if(lowBound >= highBound) {
            int tmp = highBound;
            highBound = lowBound;
            lowBound = tmp;
        }
        System.out.println("Please enter a list of numbers, all between " + lowBound + " and " + highBound + ", inclusive: ");
        do{
            String input = in.nextLine();
            String[] inputSplit = input.split(",");
            try {
                for(String s : inputSplit) {
                    int num = Integer.parseInt(s);
                    if((num >= lowBound && num <= highBound) && !intArrayList.contains(num)) {
                        intArrayList.add(num);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input entered. Please enter only numbers.");
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid input entered. Please enter a list of numbers, " +
                        "all between " + lowBound + " and " + highBound + ", inclusive: ");
            }
        } while (!valid);
        return intArrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    //Print place token error
    public void showPlaceTokenError() {
        System.out.println("Cant place wildlife token on tile.");
    }

    public void showRotateError() {
        System.out.println("Cant rotate tile. Please Select a tile.");
    }

    //Displays game over
    public void showGameOver(String playerWon) {
        System.out.println(playerWon + "won!!");
        System.out.println("Game over.");
    }

    //Displays quitting game
    public void showQuit() {
        System.out.println("Quitting game.");
    }

    public void showDeckEmpty() {
        System.out.println("The deck is empty, the cores are being calculated!!");
    }

    //Returns command
    public Command getCommand() {
        return command;
    }


    public void showFinalScore(ArrayList<String> players) {
        System.out.println("");
    }

    public void cullAllRequired() {
        System.out.println("A necessary cull has been detected and will now be enacted");
    }

    public void optionalCullAvailable() {
        System.out.println("An optional cull is available, Y to proceed with cull N to skip optional cull.");
    }

    public void optionalCullHasBeenCompleted() {
        System.out.println("Optoinal cull has been performed");
    }

    public void optionalCullCarriedOutAlready() {
        System.out.println("Optional cull detected but no longer available as you already done one this turn");
    }

    public void natureTokensAvailable() {
        System.out.print("You have nature tokens available, press /*BLANK*/ to choose a tile and wildlife of your preference ");
        System.out.println("or press /*BLANK*/ to cull any number of wildlife tokens");
    }

    public void displayScore() {
        System.out.println("Players\t\tScore");
        System.out.println("-----------------");
        for (int i = 0; i < Casc.playerNum; i++) {
            System.out.println(Casc.usersArr.get(i) + "\t\t" + Casc.usersArr.get(i).getScore());
        }
    }

}


