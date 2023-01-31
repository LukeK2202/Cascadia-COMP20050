import java.util.ArrayList;

public class Command {
    //Main Control Class, Purpose to call commands.

    //Array list to contain all named users and the play order in which they are played
    ArrayList<String> Players = new ArrayList<String>();

    //Command method, recieves input, formats and sets the command settings to the received command,
    //to be used in Casc class
    Command(String input) {

    }


    //Input validity checker, checks to ensure user input is only a valid input, return false if invalid
    public static boolean isValid(String input) {
        return true;
    }
}
