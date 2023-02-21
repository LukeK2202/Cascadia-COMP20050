import java.util.ArrayList;
import java.util.Collections;

public class Casc {
    //Main java File, will use this to start the game.
    public static ArrayList<User> usersArr = new ArrayList<>();
    public static int playerNum = 3;
    public static User currUser;

    public static void main(String[] args) {
        View view = new View();
        Command command;
        view.welcomeMsg();
        int userIndex = 0;
        playerNum = view.getPlayerNum();
        for(String p: view.getplayerNames(playerNum)) {
            usersArr.add(new User(p));
        }
        Collections.shuffle(usersArr);
        currUser = usersArr.get(0);
        Board currBoard = currUser.getBoard();
        Table table = new Table();
        do {
            view.printTable(table);
            view.printBoard(currBoard);
            System.out.println(currUser);
            boolean commDone = false;
            do {
                command = view.getUserInput();
                if(command.isQuit()) {
                    commDone = true;
                } else if(command.isNext()) {
                    userIndex = (userIndex + 1) % playerNum;
                    currUser = usersArr.get(userIndex);
                    currBoard = currUser.getBoard();
                    commDone = true;
                }
            } while(!commDone);
        } while(!command.isQuit());
        if(command.isQuit()) {
            view.showQuit();
        }
    }
}
