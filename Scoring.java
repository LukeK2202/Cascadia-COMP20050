import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Scoring {

    /**
     * Returns arraylist of the neighbouring tiles of the given tile, if the 'i' value of the tile coordinate is an odd number
     * @param tileCoord the given tile that we want to find the neighbours of
     * @param currentUserBoard Gives the current user's board to navigate the tiles on the board
     * @return ArrayList<int[]> which contains all the neighbouring tiles of the given tile which are placed on the board
     * and their placedToken status is not null, meaning they have a token placed on them
     */
    public ArrayList<int[]> adjacentTilesOdd(int[] tileCoord, Board currentUserBoard) {
        ArrayList<int[]> adjacent = new ArrayList<int[]>();
        final ArrayList<int[]> occupiedTiles = currentUserBoard.getOccupiedTileArray();
        int[] tempHolder;

        for(int i = tileCoord[0] - 1; i <= tileCoord[0] + 1; i++) {
            for(int j = tileCoord[1]; j <= tileCoord[1] + 1; j++) {
                if (i != tileCoord[0] || j != tileCoord[1]) {
                    tempHolder = new int[]{i, j};


                    if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder) && currentUserBoard.getTile(tempHolder[0], tempHolder[1]).getPlacedToken() != null) {
                        adjacent.add(tempHolder);
                    }
                }
            }
        }
        tempHolder = new int[]{tileCoord[0], tileCoord[1] - 1};
        if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder) && currentUserBoard.getTile(tempHolder[0], tempHolder[1]).getPlacedToken() != null) {
            adjacent.add(tempHolder);
        }

        return adjacent;
    }

    /**
     * Returns arraylist of the neighbouring tiles of the given tile, if the 'i' value of the tile coordinate is an even number
     * @param tileCoord the given tile that we want to find the neighbours of
     * @param currentUserBoard Gives the current user's board to navigate the tiles on the board
     * @return ArrayList<int[]> which contains all the neighbouring tiles of the given tile which are placed on the board
     * and their placedToken status is not null, meaning they have a token placed on them
     */
    public ArrayList<int[]> adjacentTilesEven(int[] tileCoord, Board currentUserBoard) {
        ArrayList<int[]> adjacent = new ArrayList<int[]>();
        final ArrayList<int[]> occupiedTiles = currentUserBoard.getOccupiedTileArray();
        int[] tempHolder;

        for(int i = tileCoord[0] - 1; i <= tileCoord[0] + 1; i++) {
            for(int j = tileCoord[1] - 1; j <= tileCoord[1]; j++) {
                if (i != tileCoord[0] || j != tileCoord[1]) {
                    tempHolder = new int[]{i, j};

                    if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder) && currentUserBoard.getTile(tempHolder[0], tempHolder[1]).getPlacedToken() != null) {
                        adjacent.add(tempHolder);
                    }
                }
            }
        }
        tempHolder = new int[]{tileCoord[0], tileCoord[1] + 1};
        if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder) && currentUserBoard.getTile(tempHolder[0], tempHolder[1]).getPlacedToken() != null) {
            adjacent.add(tempHolder);
        }

        return adjacent;
    }

    /**
     * locates all the tiles in the board that have a HAWK_PLACED token on them and calculates the score for each hawk
     * @param currentUserBoard is the current user's board that will be used to navigate the tiles
     * @return amount of points user received for the hawk scorecard
     */
    public int hawkScoreCardA(Board currentUserBoard) {
        int amount = 0;
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.HAWK_PLACED);
        for(int[] hawkCoord : wildlifePositions) {
            int cycles = 0;
            ArrayList<int[]> adjacent = getNeighbourTilesHelper(currentUserBoard, hawkCoord);
            if(adjacent.size() == 0) amount++;

            else{
                for (int[] neighbourCoord : adjacent) {
                    if (currentUserBoard.getTile(neighbourCoord[0], neighbourCoord[1]).getPlacedToken().equals(Wildlife.HAWK_PLACED))
                        break;
                    cycles++;
                }
                if (cycles >= adjacent.size()) amount++;
            }
        }
        if(amount == 0) return amount;
        else if(amount <= 5) {
            return 2 + (3 * (amount - 1));
        }
        else {
            if(amount > 8) return 26;
            else return 18 + (4 * (amount - 6));
        }
    }

//    public int bearScoreCardA(Board currentUserBoard) {
//        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.BEAR_PLACED);
//        for(int i = 0; i < wildlifePositions.size(); i++) {
//            ArrayList<int[]> adjacent = getNeighbourTilesHelper(currentUserBoard, wildlifePositions.get(i));
//
//        }
//    }

    /**
     * locates all the tiles in the board that have a FOX_PLACED token on them and calculates the score for each fox
     * @param currentUserBoard is the current user's board that will be used to navigate the tiles
     * @return amount of points the user got for the fox scorecard
     */
    public int foxScoreCardA(Board currentUserBoard) {
        int amount = 0;
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.FOX_PLACED);
        for(int[] foxCoord : wildlifePositions) {
            ArrayList<int[]> adjacent = getNeighbourTilesHelper(currentUserBoard, foxCoord);
            ArrayList<Wildlife> wildlifeContainment = new ArrayList<Wildlife>();
            for(int[] neighbourCoord : adjacent) {
                wildlifeContainment.add(currentUserBoard.getTile(neighbourCoord[0], neighbourCoord[1]).getPlacedToken());
            }
            Set<Wildlife> wildlifeSet = new HashSet<Wildlife>(wildlifeContainment);
            amount += wildlifeSet.size();
        }
        return amount;
    }

    /**
     * Helper method to find the all the tiles with the specified wildlife on them to avoid repetition in each scorecard
     * @param userBoard is the current user's board that will be used to navigate the tiles
     * @param wildlife is the specified wildlife that needs to be found
     * @return Arraylist<int[]> of all the tiles that exist on the board that have the specified wildlife placed on them
     */
    public ArrayList<int[]> getArrayOfWildlifeHelper(Board userBoard, Wildlife wildlife) {
        ArrayList<int[]> wildlifeArray = new ArrayList<int[]>();
        for(int[] coord : userBoard.getOccupiedTileArray()) {
            if(userBoard.getTile(coord[0], coord[1]).getPlacedToken() == null) continue;
            if(userBoard.getTile(coord[0], coord[1]).getPlacedToken().equals(wildlife)) {
                wildlifeArray.add(coord);
            }
        }
        return wildlifeArray;
    }

    /**
     * Helper method to find the neighbours of a given wildlife to avoid repetition in the scorecards
     * @param userBoard is the current user's board that will be used to navigate the tiles
     * @param wildlifeCoord the tile coord given that the neighbours have to be found for
     * @return ArrayList<int[]> consisting of all neighbouring tiles of the specified tile depending on the 'i' value of the
     * coord given
     */
    public ArrayList<int[]> getNeighbourTilesHelper(Board userBoard, int[] wildlifeCoord) {
        ArrayList<int[]> neighbours = new ArrayList<int[]>();
        if (wildlifeCoord[0] % 2 == 0) {
            neighbours = adjacentTilesEven(wildlifeCoord, userBoard);
        } else {
            neighbours = adjacentTilesOdd(wildlifeCoord, userBoard);
        }
        return neighbours;
    }

}