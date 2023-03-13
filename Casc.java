import Exceptions.CantPlaceTileException;

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
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        playerNum = view.getPlayerNum();
        //Iterates through the users array and receives the player name for each user.
        for(String p: view.getplayerNames(playerNum)) {
            usersArr.add(new User(p));
        }
        view.clearView();
        view.displayCommands();
        //shuffles user array to get a random order
        Collections.shuffle(usersArr);
        //Sets current user to the first one in the array and sets the current board to the board of that user
        currUser = usersArr.get(0);
        Board currBoard = currUser.getBoard();
        //Creates a table
        Table table = new Table();
        do {
            //Display the table and the current board
            view.skipLines();
            view.printTable(table);
            System.out.println("Current Players Board: " + currUser.getName());
            currBoard.checkPLaceableArea();
            currBoard.displayAreas();
            view.printBoard(currBoard);
            boolean commDone = false;

            if(table.hadSelected()) {
                view.displaySelected(table);
            }
            //Begins the loop to receive a command
            do {
                if(table.deckIsEmpty()) {
                    commDone = true;
                    command = new Command("deckFin");
                }
                int cullDecision = table.cullDetectionMethod();
                if(cullDecision == 2) {
                    view.cullAllRequired();
                    table.cullAllCall();
                }
                else if(cullDecision  == 1) {
                    if(currUser.getOptionalCullCarriedOut()) {
                        view.optionalCullCarriedOutPreviousRound();
                        currUser.setOptionalCullPreviouslyDone();
                    }
                    else {
                        view.optionalCullAvailable();
                        //takes user input
                        //if yes carry out optional cull
                        table.optionalCull();
                        currUser.setOptionalCullDoneNow();
                        view.optionalCullHasBeenCompleted();
                        //sets cull done now aka sets the value to true
                    }
                }
                //receive user input
                command = view.getUserInput();
                view.clearView();
                //If command is quit sets commDone to true to exit loop
                if(command.isQuit()) {
                    commDone = true;
                    //If command is next then go to the next user and set the current board to their board
                } else if(command.isNext()) {
                    //sets optional cull to false, just in case it was true before so that optional cull is available next time fore the user
                    currUser.setOptionalCullPreviouslyDone();
                    userIndex = (userIndex + 1) % playerNum;
                    currUser = usersArr.get(userIndex);
                    currBoard = currUser.getBoard();
                    commDone = true;
                } else if(command.isComm()) {
                    view.displayCommands();
                    commDone = true;
                } else if(command.isSelect()) {
                    table.selectColumn(command.getSelected());
                    commDone = true;
                } else if(command.isRotate()) {
                    if(!table.hadSelected()) {
                        view.showRotateError();
                    } else {
                        table.getSelectedTile().rotate();
                        commDone = true;
                    }
                } else if(command.isPlace()) {
                    try {
                        currBoard.placeTile(table.getSelectedTile(), command.getSelected());
                        table.unselectTile();
                        commDone = true;
                    } catch(CantPlaceTileException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } while(!commDone);

        } while(!command.isQuit() && !command.DeckisFin());
        //If command is quit show quit game.
        if(command.isQuit()) {
            view.showQuit();
        }
        else if(command.DeckisFin()) {
            view.showDeckEmpty();
        }
    }
}
