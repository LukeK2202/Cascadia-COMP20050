import java.util.ArrayList;
import java.util.Scanner;

public class Command{
    //Main Control Class, Purpose to call commands.

    private enum CommandType {QUIT, NEXT};
    private CommandType commandType;

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

    //Checks if the is quit command was given
    public boolean isQuit() {
        if(commandType == CommandType.QUIT) {
            return true;
        } else {
            return false;
        }
    }

    //Checks if the next command was given
    public boolean isNext() {
        if(commandType == CommandType.NEXT) {
            return true;
        } else {
            return false;
        }
    }
    
}
