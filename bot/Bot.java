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
    WildlifeLogic placLog = new WildlifeLogic();

    private Board testBoard;

    Bot(String name) {
        super(name);
    }

    //keeps location of what tile to place token on, or -1, -1 if no such combination exists
    int[] tileLocationForToken = {-1, -1};

    public void setTileLocationForToken(int[] loc) {
        tileLocationForToken = loc;
    }

    public int[] getTileLocationForToken() {
        return tileLocationForToken;
    }

    public void setTestBoard(Board board) {
        this.testBoard = Board.getBoardCopy(board);
    }

    public Board getTestBoard() {
        return testBoard;
    }

    /**
     * Selects tile, token pair, based on point yield
     * @param table table currently in play this game
     * @return which pair to select
     */
    public Command pickSelectTile(Table table) {
        final ArrayList<Tile> shownTiles = table.getShownTiles();
        final ArrayList<Wildlife> shownWildlife = table.getShownWildlife();

        int[] scores = selectTileTokenPair(shownWildlife);
        int maxPoints = 0;
        int index = 0;

        for(int j = 0; j < scores.length; j++) {
            if(scores[j] > maxPoints) {
                maxPoints = scores[j];
                index = j;
            }
        }
        if(maxPoints < 2) {
            if(this.getNatureTokens() > 0) {
                return new Command("N");
            } else if (maxPoints < 0) {
                return new Command("S" + rn.nextInt(1, 5));
            } else {
                return new Command("S" + (index + 1));
            }
        }
        return new Command("S" + (index + 1));
        
        
    }

    /**
     * Selects the best of the best, gets index which will result in the highest point yield
     * @param shownWildlife wildlife that is displayed on the table right now
     * @return index of which tile, token pair to choose
     */
    public int[] selectTileTokenPair(ArrayList<Wildlife> shownWildlife) {
        int maxPoints = 0;
        int index = 0;
        int[] scores = new int[shownWildlife.size()];
        ArrayList<int[]> primeLocations = new ArrayList<int[]>();

        for(int i = 0; i < shownWildlife.size(); i++) {
            ArrayList<int[]> possibleLocations = placLog.possibleLocationsForWildlife(shownWildlife.get(i), this.getBoard());
            if(possibleLocations.size() == 0) {
                scores[i] = -1;
                primeLocations.add(new int[]{-1, -1});
                continue;
            }
            primeLocations.add(placLog.wildlifePointPlacementLogic(possibleLocations, shownWildlife.get(i), this));
            if(primeLocations.get(i)[0] == -1) {
                scores[i] = -1;
                continue;
            }
            scores[i] = placLog.bestScoreForLocation(this, primeLocations.get(i), shownWildlife.get(i));
        }

        for(int j = 0; j < scores.length; j++) {
            if(scores[j] > maxPoints) {
                maxPoints = scores[j];
                index = j;
            }
        }
        if(maxPoints < 0) {
            setTileLocationForToken(new int[] {-1, -1});
        } else {
        setTileLocationForToken(primeLocations.get(index));
        }

        return scores;


    }

    public Command pickPlaceTile() {
        Board board = getBoard();
        final ArrayList<int[]> placeableArr = board.getPlaceableTileArray();

        int pickedNum = rn.nextInt(0, placeableArr.size());
        return new Command("P" + pickedNum);
    }

    public boolean optionalCullInput(){
        return rn.nextBoolean();
    }


    public boolean botYorN() {
        int[] checker = getTileLocationForToken();
        return checker[0] > 0;
    }


    /**
     * Places token where it will get the highest points
     * @param botBoard The board of the current bot
     * @return which tile to place token on
     */
    public Command placeWildlife(Board botBoard) {
        int[] place = getTileLocationForToken();

        return new Command("P" + botBoard.getTile(place[0], place[1]).getCoOrd());
    }

    public int natureTokenDecider() {
        return 1;
    }

    public int[] natureCull() {
        return new int[] {1,2,3,4};
    }
}
