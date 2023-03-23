import java.util.ArrayList;
import java.util.Arrays;

public class Scoring {

    public ArrayList<int[]> adjacentTilesOdd(int[] tileCoord, Board currentUserBoard) {
        ArrayList<int[]> adjacent = new ArrayList<int[]>();
        final ArrayList<int[]> occupiedTiles = currentUserBoard.getOccupiedTileArray();
        int[] tempHolder;

        for(int i = tileCoord[0] - 1; i <= tileCoord[0] + 1; i++) {
            for(int j = tileCoord[1]; j <= tileCoord[1] + 1; j++) {
                if (i != tileCoord[0] || j != tileCoord[1]) {
                    tempHolder = new int[]{i, j};


                    if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder)) {
                        adjacent.add(tempHolder);
                    }
                }
            }
        }
        tempHolder = new int[]{tileCoord[0], tileCoord[1] - 1};
        if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder)) {
            adjacent.add(tempHolder);
        }

        return adjacent;
    }
    public ArrayList<int[]> adjacentTilesEven(int[] tileCoord, Board currentUserBoard) {
        ArrayList<int[]> adjacent = new ArrayList<int[]>();
        final ArrayList<int[]> occupiedTiles = currentUserBoard.getOccupiedTileArray();
        int[] tempHolder;

        for(int i = tileCoord[0] - 1; i <= tileCoord[0] + 1; i++) {
            for(int j = tileCoord[1] - 1; j <= tileCoord[1]; j++) {
                if (i != tileCoord[0] || j != tileCoord[1]) {
                    tempHolder = new int[]{i, j};

                    if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder)) {
                        adjacent.add(tempHolder);
                    }
                }
            }
        }
        tempHolder = new int[]{tileCoord[0], tileCoord[1] + 1};
        if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder)) {
            adjacent.add(tempHolder);
        }

        return adjacent;
    }
    public int hawkAScoreCard(Board currentUserBoard) {
        ArrayList<int[]> adjacent;
        int amount = 0;
        for(int i = 0; i < currentUserBoard.getOccupiedTileArray().size(); i++) {
            int cycles = 0;
            int[] coordHolder = currentUserBoard.getOccupiedTileArray().get(i);
            if(currentUserBoard.getTile(coordHolder[0], coordHolder[1]).showPlacedToken().equals(Wildlife.HAWK_PLACED)) {
                if(coordHolder[0] % 2 == 0) {
                    adjacent = adjacentTilesEven(coordHolder, currentUserBoard);
                }
                else {
                    adjacent = adjacentTilesOdd(coordHolder, currentUserBoard);
                }

                for(int j = 0; j < adjacent.size(); j++) {
                    coordHolder = adjacent.get(j);
                    if(currentUserBoard.getTile(coordHolder[0], coordHolder[1]).showPlacedToken().equals(Wildlife.HAWK_PLACED)) break;
                    cycles++;
                    if(cycles == 6) amount++;
                }
            }
        }
        if(amount == 0) return amount;
        if(amount <= 5) {
            return 2 + (3 * amount);
        }
        else {
            if(amount > 8) return 26;
            else return 16 + (4 * amount);
        }
    }

    public int bearAScoreCard(Board currentUserBoard) {
        ArrayList<int[]> adjacent;
        int amount = 0;
        for (int i = 0; i < currentUserBoard.getOccupiedTileArray().size(); i++) {
            //int cycles = 0;
            int[] coordHolder = currentUserBoard.getOccupiedTileArray().get(i);
            if (currentUserBoard.getTile(coordHolder[0], coordHolder[1]).showPlacedToken().equals(Wildlife.BEAR_PLACED)) {
                if (coordHolder[0] % 2 == 0) {
                    adjacent = adjacentTilesEven(coordHolder, currentUserBoard);
                } else {
                    adjacent = adjacentTilesOdd(coordHolder, currentUserBoard);
                }
            }
        }
    }

    public int FoxAScoreCard(Board currentUserBoard) {

    }

    public ArrayList<int[]>

}