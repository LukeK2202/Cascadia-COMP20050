
/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

public class Command{
    //Main Control Class, Purpose to call commands.

    private enum CommandType {QUIT, NATURE, COMMAND, SELECT, ROTATE, PLACE}
    private CommandType commandType;
    private int selected;

    //Command method, recieves input, formats and sets the command settings to the received command,
    //to be used in Casc class
    Command(String input) {
        String inputFormat = input.trim().toUpperCase();
        if(inputFormat.equals("Q")) {
            commandType = CommandType.QUIT;
        }
        else if(inputFormat.equals("N")) {
            commandType = CommandType.NATURE;
        }
        else if(inputFormat.equals("C")) {
            commandType = CommandType.COMMAND;
        }
        else if(inputFormat.matches("[S][1-4]")) {
            commandType = CommandType.SELECT;
            selected = Character.getNumericValue(inputFormat.charAt(1));
        }
        else if(inputFormat.equals("R")) {
            commandType = CommandType.ROTATE;
        }
        else if(inputFormat.matches("[P]\\d{1,2}")) {
            commandType = CommandType.PLACE;
            selected = Integer.parseInt(inputFormat.substring(1));
        }

    }


    //Input validity checker, checks to ensure user input is only a valid input, return false if invalid
    public static boolean isValid(String input) {
       String inputFormat = input.trim().toUpperCase();
       return inputFormat.equals("Q") || inputFormat.equals("N") || inputFormat.equals("C") || inputFormat.matches("[S][1-4]") || inputFormat.equals("R")
               || inputFormat.matches("[P]\\d{1,2}");

    }

    //Checks if the is quit command was given
    public boolean isQuit() {
        if(commandType == CommandType.QUIT) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSelect() {
        return commandType == CommandType.SELECT;
    }

    public boolean isRotate() {
        return commandType == CommandType.ROTATE;
    }

    //Checks if the next command was given
    public boolean isNature() {
        if(commandType == CommandType.NATURE) {
            return true;
        } else {
            return false;
        }
    }

    //Checks if the command command was given
    public boolean isComm() {
        if(commandType == CommandType.COMMAND) {
            return true;
        } else {
            return false;
        }
    }


    public int getSelected() {
        return selected;
    }

    public boolean isPlace() {
        return commandType == CommandType.PLACE;
    }
}
