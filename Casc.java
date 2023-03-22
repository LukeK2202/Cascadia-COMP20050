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
        int natureTokens = 20;
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
            System.out.println("Players Nature Tokens: " + currUser.getNatureTokens());
            currBoard.checkPLaceableArea();
            currBoard.displayAvailableAreas();
            view.printBoard(currBoard);
            boolean commDone = false;

            //Check for if any tile or wildlife selected should be displayed
            if(table.hadSelectedTile() || table.hadSelectedWildlife()) {
                view.displaySelected(table);
            }
            //Begins the loop to receive a command
            do {
                if (table.deckIsEmpty()) {
                    commDone = true;
                    command = new Command("deckFin");
                }
                //Beginning of cull code block
                int cullDecision;
                do {
                    cullDecision = table.cullDetectionMethod();
                    //If all 4 are matching, cull all
                    if (cullDecision == 2) {
                        table.cullAllCall();

                        //Block to print out updated table
                        view.skipLines();
                        view.printTable(table);
                        System.out.println("Current Players Board: " + currUser.getName());
                        System.out.println("Players Nature Tokens: " + currUser.getNatureTokens());
                        currBoard.checkPLaceableArea();
                        currBoard.displayAvailableAreas();
                        view.printBoard(currBoard);

                        view.cullAllRequired();
                        //If 3 are matching, optional cull
                    } else if (cullDecision == 1) {
                        //Check if the user has already used an optional cull this turn
                        if (currUser.getOptionalCullCarriedOut()) {
                            view.optionalCullCarriedOutAlready();
                            cullDecision = 0;
                        } else {
                            //Else get user input for if they want cull or not
                            view.optionalCullAvailable();
                            boolean input = view.getUserYorN();
                            if (input) {
                                table.optionalCull();
                                currUser.setOptionalCullDoneNow();

                                view.skipLines();
                                view.printTable(table);
                                System.out.println("Current Players Board: " + currUser.getName());
                                System.out.println("Players Nature Tokens: " + currUser.getNatureTokens());
                                currBoard.checkPLaceableArea();
                                currBoard.displayAvailableAreas();
                                view.printBoard(currBoard);

                                view.optionalCullHasBeenCompleted();
                            }
                            else {
                                cullDecision = 0;
                            }
                        }
                    }
                } while (cullDecision != 0);
                //receive user input
                command = view.getUserInput();
                //If command is quit sets commDone to true to exit loop
                if (command.isQuit()) {
                    commDone = true;
                    //If command is next then go to the next user and set the current board to their board
                } else if (command.isNext()) {
                    //sets optional cull to false, just in case it was true before so that optional cull is available next time fore the user
                    currUser.setOptionalCullPreviouslyDone();
                    userIndex = (userIndex + 1) % playerNum;
                    currUser = usersArr.get(userIndex);
                    currBoard = currUser.getBoard();
                    commDone = true;
                } else if (command.isComm()) {
                    view.displayCommands();
                    commDone = true;
                } else if (command.isSelect()) {
                    table.selectColumn(command.getSelected());
                    commDone = true;
                } else if (command.isRotate()) {
                    if (!table.hadSelectedTile()) {
                        view.showRotateError();
                    } else {
                        table.getSelectedTile().rotate();
                        commDone = true;
                    }
                } else if (command.isPlace()) {
                    boolean bothPlaced = false;
                    try {
                        if (table.hadSelectedTile()) {
                            currBoard.placeTile(table.getSelectedTile(), command.getSelected());
                            table.unselectTile();

                            currBoard.hideAvailableAreas();
                            currBoard.displayPlacedAreas();

                            view.skipLines();
                            view.printTable(table);
                            System.out.println("Current Players Board: " + currUser.getName());
                            System.out.println("Players Nature Tokens: " + currUser.getNatureTokens());
                            view.printBoard(currBoard);
                            view.displaySelected(table);
                        } else if (!table.hadSelectedWildlife()) {
                            throw new CantPlaceTileException("No Tile Selected. Please select a tile.");
                        }
                        do {
                            System.out.println("Would you like to place wildlife token?");
                            boolean input = view.getUserYorN();
                            if (input) {
                                command = view.getUserInput();
                                try {
                                    if (command.isPlace()) {
                                        currBoard.placeWildlife(command.getSelected(), table.getSelectedWildlife());
                                        int[] coOrd = currBoard.getTilePlacedCoOrd(command.getSelected());
                                        if(currBoard.getTile(coOrd[0], coOrd[1]).isKeystoneTile()) {
                                            if(natureTokens > 0) {
                                                currUser.addNatureToken();
                                                natureTokens--;
                                                System.out.println("Given nature token for placing on keystone tile.");
                                            } else {
                                                System.out.println("Couldn't give nature token as none are available.");
                                            }
                                        }
                                        table.unselectWildlife();
                                        bothPlaced = true;
                                        commDone = true;

                                        currBoard.hidePlacedAreas();
                                        currBoard.displayAvailableAreas();
                                    }
                                } catch (CantPlaceWildlifeException ex) {
                                    System.out.println(ex.getMessage());
                                }

                            } else {
                                table.unselectWildlife();
                                bothPlaced = true;
                                commDone = true;

                                currBoard.hidePlacedAreas();
                                currBoard.displayAvailableAreas();
                            }
                        } while (!bothPlaced);
                    } catch (CantPlaceTileException ex) {
                        System.out.println(ex.getMessage());
                    }
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
