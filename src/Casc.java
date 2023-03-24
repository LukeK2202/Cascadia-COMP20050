import Exceptions.*;

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
        Scoring scoreBoard = new Scoring();
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
            boolean turnOver = false;
            //Display the table and the current board
            currBoard.checkPLaceableArea();
            currBoard.displayAvailableAreas();
            view.displayScreen(currUser, currBoard, table);

            boolean commDone = false;
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
                        view.displayScreen(currUser, currBoard, table);

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

                                view.displayScreen(currUser, currBoard, table);

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
                } else if (command.isNature()) {
                    //sets optional cull to false, just in case it was true before so that optional cull is available next time fore the user
                    if(currUser.getNatureTokens() > 0) {
                        System.out.println("Spend a nature token to: ");
                        System.out.println("(0) to cancel, (1) Cull any number of wildlife tokens, or (2) Take any combination of tile and wildlife token: ");
                        int userInputInt = view.getUserint(0, 2);
                        if(userInputInt == 1) {
                            System.out.println("Please enter the numbers of the wildlife you would like to cull (e.g. 1,3,4 to cull positions 1, 3 and 4): ");
                            int[] userCullPos = new int[0];
                            do {
                                userCullPos = view.getUserintArray(1, 4);
                            } while (userCullPos.length > 4);
                            for(int n = 0; n < userCullPos.length; n++) {
                                userCullPos[n]--;
                            }
                            table.natureTokenCull(userCullPos);
                            currUser.removeNatureToken();
                            natureTokens++;
                            commDone = true;
                        } else if(userInputInt == 2) {
                            System.out.println("Please enter the number of the tile you would like to select: ");
                            int userSelectTile = view.getUserint(1, 4);
                            table.selectTile(userSelectTile);

                            view.displayScreen(currUser, currBoard, table);

                            System.out.println("Please enter the number of the wildlife you would like to select: ");
                            int userSelectWildlife = view.getUserint(1, 4);
                            table.selectWildlife(userSelectWildlife);

                            currUser.removeNatureToken();
                            natureTokens++;
                            commDone = true;
                        } else {
                        }
                    } else {
                        System.out.println("No nature tokens to spend.");
                    }
                } else if (command.isComm()) {
                    view.displayCommands();
                    commDone = true;
                } else if (command.isSelect()) {
                        table.SelectTileWild(command.getSelected());
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
                            view.displayScreen(currUser, currBoard, table);


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
                                        currBoard.checkPLaceableArea();
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
                            turnOver = true;
                        } while (!bothPlaced);

                    } catch (CantPlaceTileException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } while(!commDone);
            if(turnOver) {
                table.fillTable();
                view.displayScreen(currUser, currBoard, table);
                System.out.println("Press 1 to move to next player.");
                int userNext = 0;
                do {
                    userNext = view.getUserint(1, 1);
                } while(userNext != 1);
                currUser.setOptionalCullPreviouslyDone();
                userIndex = (userIndex + 1) % playerNum;
                currUser = usersArr.get(userIndex);
                currBoard = currUser.getBoard();
                System.out.println("Turn Over. Moving to next user.");
            }
            view.clearView();

        } while(!command.isQuit() && !command.DeckisFin());
        //If command is quit show quit game.
        if(command.isQuit()) {
            view.showQuit();
            System.out.println(scoreBoard.hawkScoreCardA(currBoard));
        }
        else if(command.DeckisFin()) {
            view.showDeckEmpty();
        }
    }
}
