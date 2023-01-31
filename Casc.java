import java.util.ArrayList;

public class Casc {
    //Main java File, will use this to start the game.

    public static void main(String[] args) {
        View view = new View();

        view.welcomeMsg();

        int test = view.getPlayerNum();

        ArrayList<String> players = view.getplayerNames(test);

        System.out.println(players);

    }
}
