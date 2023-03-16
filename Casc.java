import Exceptions.CantPlaceTileException;
import Exceptions.CantPlaceWildlifeException;

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
            currBoard.displayAvailableAreas();
            view.printBoard(currBoard);
            boolean commDone = false;

            if(table.hadSelectedTile() || table.hadSelectedWildlife()) {
                view.displaySelected(table);
            }
            //Begins the loop to receive a command
            do {
                if(table.deckIsEmpty()) {
                    commDone = true;
                    command = new Command("deckFin");
                }
                //receive user input
                int cullDecision = table.cullDetectionMethod();
                if(cullDecision == 2) {
                    view.cullAllRequired();
                    table.cullAllCall();
                }
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
                } else if(command.isComm()) {
                    view.displayCommands();
                    commDone = true;
                } else if(command.isSelect()) {
                    table.selectColumn(command.getSelected());
                    commDone = true;
                } else if(command.isRotate()) {
                    if(!table.hadSelectedTile()) {
                        view.showRotateError();
                    } else {
                        table.getSelectedTile().rotate();
                        commDone = true;
                    }
                } else if(command.isPlace()) {
                    boolean bothPlaced = false;
                    do {
                        try {
                            if(table.hadSelectedTile()) {
                                currBoard.placeTile(table.getSelectedTile(), command.getSelected());
                                table.unselectTile();

                                currBoard.hideAvailableAreas();
                                currBoard.displayPlacedAreas();

                                view.skipLines();
                                view.printTable(table);
                                System.out.println("Current Players Board: " + currUser.getName());
                                view.printBoard(currBoard);
                                view.displaySelected(table);
                            } else if (!table.hadSelectedWildlife()){
                                throw new CantPlaceTileException("No Tile Selected. Please select a tile.");
                            }


                            command = view.getUserInput();

                            if(command.isPlace()) {
                                currBoard.placeWildlife(command.getSelected(), table.getSelectedWildlife());
                                table.unselectWildlife();
                                bothPlaced = true;

                                currBoard.hidePlacedAreas();
                                currBoard.displayAvailableAreas();
                            }

                        } catch(CantPlaceTileException ex) {
                            System.out.println(ex.getMessage());
                            break;
                        } catch (CantPlaceWildlifeException ex) {
                            System.out.println(ex.getMessage());
                        }
                    } while(!bothPlaced);
                    commDone = true;
                }
            } while(!commDone);
            view.clearView();

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
