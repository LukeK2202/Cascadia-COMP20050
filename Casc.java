import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Casc {
    //Main java File, will use this to start the game.
    public static ArrayList<User> usersArr = new ArrayList<>();
    public static int playerNum;
    public static User currUser;

    public static void main(String[] args) {
        View view = new View();
        Deck deck = new Deck();
        Board board = new Board();
        Command command;
        view.welcomeMsg();
        int userIndex = 0;
        playerNum = view.getPlayerNum();
        for(String p: view.getplayerNames(playerNum)) {
            usersArr.add(new User(p));
        }
        Collections.shuffle(usersArr);
        currUser = usersArr.get(0);
        do {
            view.printBoard(board);

            System.out.println(currUser);
            boolean commDone = false;
            do {
                command = view.getUserInput();
                if(command.isQuit()) {
                    commDone = true;
                } else if(command.isNext()) {
                    userIndex = (userIndex + 1) % playerNum;
                    currUser = usersArr.get(userIndex);
                    commDone = true;
                }
            } while(!commDone);
        } while(!command.isQuit());
        if(command.isQuit()) {
            view.showQuit();
        }
    }
}
