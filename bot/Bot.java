import java.util.ArrayList;
import java.util.Random;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

 //Main class for bot, a subclass of user
public class Bot extends User{

    Random rn = new Random();

    Bot(String name) {
        super(name);
    }

    //Template ideas for making the bot give input and commands into the main casc game
    public Command pickSelectTile(Table table) {
        final ArrayList<Tile> shownTiles = table.getShownTiles();
        final ArrayList<Wildlife> shownWildlife = table.getShownWildlife();

        
        int pickedNum = rn.nextInt(1, 5);
        return new Command("S" + pickedNum);
    }

    public Command pickPlaceTile() {
        Board board = getBoard();
        final ArrayList<int[]> placeableArr = board.getPlaceableTileArray();

        int pickedNum = rn.nextInt(0, placeableArr.size() - 1);
        return new Command("P" + pickedNum);
    }
}
