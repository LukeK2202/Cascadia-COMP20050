import java.util.ArrayList;

public class Scoring extends View {

    public void scoreTable() {
        int points = 0;
        int numplayers = getPlayerNum();
        ArrayList<String> names = getplayerNames(numplayers);

        System.out.println("Players\t\tPoints");
        System.out.println("-----------------");

        for (int i = 0; i < numplayers; i++) {
            System.out.println(names + "\t\t" + points);
        }
    }
}