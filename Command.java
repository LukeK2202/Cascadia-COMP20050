import java.util.ArrayList;
import java.util.Scanner;

public class Command{
    //Main Control Class, Purpose to call commands.

    //Array list to contain all named users and the play order in which they are played
    static ArrayList<String> Players = new ArrayList<String>();

    private static int currentPlayerPosition = 0;

    public static int numPlayers;

    private static boolean gameInProgress = true;

    private enum CommandType {QUIT, NEXT};
    private CommandType commandType;

    private static String getCurrPlayer(){
        return Players.get(currentPlayerPosition);
    }
    private static void nextPlayer(){
        currentPlayerPosition = (currentPlayerPosition + 1) % Players.size();
    }

//    public static void quitFeature(){
//        Scanner in = new Scanner(System.in);
//
//        while (gameInProgress){
//            System.out.println("enter q to quit");
//            String input = in.nextLine();
//
//            if(input.equals("q")){
//                View.showQuit();
//            }
//            else{
//                System.out.println("continuing game");
//            }
//        }
//        in.close();
//    }

//    private static void quitGame() {
//        View.showQuit();
//        gameInProgress = false;
//    }

    //Command method, recieves input, formats and sets the command settings to the received command,
    //to be used in Casc class
    Command(String input) {
        String inputFormat = input.trim().toUpperCase();
        if(inputFormat.equals("Q")) {
            commandType = CommandType.QUIT;
        }
        else if(inputFormat.equals("N")) {
            commandType = CommandType.NEXT;
        }

    }


    //Input validity checker, checks to ensure user input is only a valid input, return false if invalid
    public static boolean isValid(String input) {
       String inputFormat = input.trim().toUpperCase();
       if(inputFormat.equals("Q") || inputFormat.equals("N")) {
           return true;
       } else {
           return false;
       }

    }

    public boolean isQuit() {
        if(commandType == CommandType.QUIT) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNext() {
        if(commandType == CommandType.NEXT) {
            return true;
        } else {
            return false;
        }
    }
    
}
