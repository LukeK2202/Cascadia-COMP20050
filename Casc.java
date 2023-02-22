import java.util.ArrayList;
import java.util.Collections;

public class Casc {
    //Main java File, will use this to start the game.

    //static array list of users that will be in the game
    public static ArrayList<User> usersArr = new ArrayList<>();
    //static int for amount of players being played
    public static int playerNum = 0;
    //Static user that is the current user in the game flow
    public static User currUser;

    //Main game
    public static void main(String[] args) {
        //initiates view and a command for the game. displays the welcome message
        View view = new View();
        Command command;
        view.welcomeMsg();
        //Sets users index to 0 (first player) and asks for how many players are playing
        int userIndex = 0;
        playerNum = view.getPlayerNum();
        //Iterates through the users array and receives the player name for each user.
        for(String p: view.getplayerNames(playerNum)) {
            usersArr.add(new User(p));
        }
        //shuffles user array to get a random order
        Collections.shuffle(usersArr);
        //Sets current user to the first one in the array and sets the current board to the board of that user
        currUser = usersArr.get(0);
        Board currBoard = currUser.getBoard();
        //Creates a table
        Table table = new Table();
        do {
            //Display the table and the current board
            view.printTable(table);
            view.printBoard(currBoard);
            boolean commDone = false;
            //Begins the loop to receive a command
            do {
                //receive user input
                command = view.getUserInput();
                //If command is quit sets commDone to true to exit loop
                if(command.isQuit()) {
                    commDone = true;
                    //If command is next then go to the next user and set the current board to their board
                } else if(command.isNext()) {
                    userIndex = (userIndex + 1) % playerNum;
                    currUser = usersArr.get(userIndex);
                    currBoard = currUser.getBoard();
                    commDone = true;
                }
            } while(!commDone);
        } while(!command.isQuit());
        //If command is quit show quit game.
        if(command.isQuit()) {
            view.showQuit();
        }
    }
}
