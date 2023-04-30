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
    PlacementLogic placLog = new PlacementLogic();

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

    /**
     * Selects tile, token pair, based on point yield
     * @param table table currently in play this game
     * @return which pair to select
     */
    public Command pickSelectTile(Table table) {
        final ArrayList<Tile> shownTiles = table.getShownTiles();
        final ArrayList<Wildlife> shownWildlife = table.getShownWildlife();

        return new Command("S" + selectTileTokenPair(shownWildlife));
    }

    /**
     * Selects the best of the best, gets index which will result in the highest point yield
     * @param shownWildlife wildlife that is displayed on the table right now
     * @return index of which tile, token pair to choose
     */
    public int selectTileTokenPair(ArrayList<Wildlife> shownWildlife) {
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
        if(maxPoints < 1) {
            setTileLocationForToken(new int[]{-1, -1});
            return rn.nextInt(1, 5);
        }
        setTileLocationForToken(primeLocations.get(index));
        return index + 1;
    }

    public Command pickPlaceTile() {
        Board board = getBoard();
        final ArrayList<int[]> placeableArr = board.getPlaceableTileArray();

        int pickedNum = rn.nextInt(0, placeableArr.size() - 1);
        return new Command("P" + pickedNum);
    }

    public boolean optionalCullInput(){
        return rn.nextBoolean();
    }

    // public boolean botYorN(Board botBoard, Wildlife selectedWildlife) {
    //     for(int[] coOrd : botBoard.getOccupiedTileArray()) {

    //         if(!botBoard.getTile(coOrd[0], coOrd[1]).hasPlacedToken()) {
    //             String tokenToCheck = selectedWildlife.getName();
    //             ArrayList<Wildlife> animals = botBoard.getTile(coOrd[0], coOrd[1]).getAnimals();

    //             for(Wildlife animal : animals) {
    //                 if(animal.getName().equals(tokenToCheck)){
    //                 return true;
    //                 }
    //             }
    //         }
    //     }
    //     return false;
    // }


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
        // ArrayList<Integer> placements = new ArrayList<Integer>();
        // for(int[] coOrd : botBoard.getOccupiedTileArray()) {
        //     if(!botBoard.getTile(coOrd[0], coOrd[1]).hasPlacedToken()) {
        //         ArrayList<Wildlife> animals = botBoard.getTile(coOrd[0], coOrd[1]).getAnimals();
        //         for(Wildlife animal : animals){
        //             if(animal.getName().equals(selectedWildlife.getName())) {
        //                 placements.add(botBoard.getTile(coOrd[0], coOrd[1]).getCoOrd());
        //             }
        //         }
        //     }
        // }

        //int pickedTile = rn.nextInt(placements.size());
        int[] place = getTileLocationForToken();

        return new Command("P" + botBoard.getTile(place[0], place[1]).getCoOrd());
    }




    // public Command placeWildlife(Board botBoard, Wildlife selectedWildlife) {
    //     ArrayList<Integer> placements = new ArrayList<Integer>();
    //     for(int[] coOrd : botBoard.getOccupiedTileArray()) {
    //         if(!botBoard.getTile(coOrd[0], coOrd[1]).hasPlacedToken()) {
    //             ArrayList<Wildlife> animals = botBoard.getTile(coOrd[0], coOrd[1]).getAnimals();
    //             for(Wildlife animal : animals){
    //                 if(animal.getName().equals(selectedWildlife.getName())) {
    //                     placements.add(botBoard.getTile(coOrd[0], coOrd[1]).getCoOrd());
    //                 }
    //             }
    //         }
    //     }

    //     int pickedTile = rn.nextInt(placements.size());

    //     return new Command("P" + placements.get(pickedTile));
    // }
}
