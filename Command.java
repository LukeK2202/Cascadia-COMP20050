public class Command{
    //Main Control Class, Purpose to call commands.

    private enum CommandType {QUIT, NEXT, COMMAND, DECK_EMPTY, SELECT, ROTATE}
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
            commandType = CommandType.NEXT;
        }
        else if(inputFormat.equals("C")) {
            commandType = CommandType.COMMAND;
        }
        else if(inputFormat.equals("deckFin")) {
            commandType = CommandType.DECK_EMPTY;
        }
        else if(inputFormat.matches("[S][1-4]")) {
            commandType = CommandType.SELECT;
            selected = Character.getNumericValue(inputFormat.charAt(1));
        }
        else if(inputFormat.equals("R")) {
            commandType = CommandType.ROTATE;
        }

    }


    //Input validity checker, checks to ensure user input is only a valid input, return false if invalid
    public static boolean isValid(String input) {
       String inputFormat = input.trim().toUpperCase();
       return inputFormat.equals("Q") || inputFormat.equals("N") || inputFormat.equals("C") ||
               inputFormat.equals("DECKFIN") || inputFormat.matches("[S][1-4]") || inputFormat.equals("R");

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
    public boolean isNext() {
        if(commandType == CommandType.NEXT) {
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

    public boolean DeckisFin() {
        if(commandType == CommandType.DECK_EMPTY) {
            return true;
        } else {
            return false;
        }
    }

    public int getSelected() {
        return selected;
    }
}
