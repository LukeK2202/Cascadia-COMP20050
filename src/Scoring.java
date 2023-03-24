import java.util.*;


public class Scoring {

    public ArrayList<int[]> adjacentTilesOdd(int[] tileCoord, Board currentUserBoard) {
        ArrayList<int[]> adjacent = new ArrayList<int[]>();
        final ArrayList<int[]> occupiedTiles = currentUserBoard.getOccupiedTileArray();
        int[] tempHolder;

        for(int i = tileCoord[0] - 1; i <= tileCoord[0] + 1; i++) {
            for(int j = tileCoord[1]; j <= tileCoord[1] + 1; j++) {
                if (i != tileCoord[0] || j != tileCoord[1]) {
                    tempHolder = new int[]{i, j};


                    if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder) &&
                            currentUserBoard.getTile(tempHolder[0], tempHolder[1]).getPlacedToken() != null) {
                        adjacent.add(tempHolder);
                    }
                }
            }
        }
        tempHolder = new int[]{tileCoord[0], tileCoord[1] - 1};
        if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder) &&
                currentUserBoard.getTile(tempHolder[0], tempHolder[1]).getPlacedToken() != null) {
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

                    if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder) &&
                            currentUserBoard.getTile(tempHolder[0], tempHolder[1]).getPlacedToken() != null) {
                        adjacent.add(tempHolder);
                    }
                }
            }
        }
        tempHolder = new int[]{tileCoord[0], tileCoord[1] + 1};
        if(currentUserBoard.isCoOrdsContained(occupiedTiles, tempHolder) &&
                currentUserBoard.getTile(tempHolder[0], tempHolder[1]).getPlacedToken() != null) {
            adjacent.add(tempHolder);
        }

        return adjacent;
    }
    public int hawkScoreCardA(Board currentUserBoard) {
        int amount = 0;
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.HAWK_PLACED);
        for(int[] hawkCoord : wildlifePositions) {
            int cycles = 0;
            ArrayList<int[]> adjacent = getNeighbourTilesHelper(currentUserBoard, hawkCoord);
            for(int[] neighbourCoord : adjacent) {
                if(currentUserBoard.getTile(neighbourCoord[0], neighbourCoord[1]).getPlacedToken().equals(Wildlife.HAWK_PLACED)) break;
                cycles++;
                if(cycles == 5) amount++;
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

//    public int bearScoreCardA(Board currentUserBoard) {
//        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.BEAR_PLACED);
//        for(int i = 0; i < wildlifePositions.size(); i++) {
//            ArrayList<int[]> adjacent = getNeighbourTilesHelper(currentUserBoard, wildlifePositions.get(i));
//
//        }
//    }

    public int FoxScoreCardA(Board currentUserBoard) {
        int amount = 0;
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.FOX_PLACED);
        for(int[] foxCoord : wildlifePositions) {
            ArrayList<int[]> adjacent = getNeighbourTilesHelper(currentUserBoard, foxCoord);
            ArrayList<Wildlife> wildlifeContainment = new ArrayList<Wildlife>();
            for(int[] neighbourCoord : adjacent) {
                wildlifeContainment.add(currentUserBoard.getTile(neighbourCoord[0], neighbourCoord[1]).getPlacedToken());
            }
            Set<Wildlife> wildlifeSet = new HashSet<Wildlife>(wildlifeContainment);
            if(wildlifeSet.size() > 5) amount += 5;
            else amount += wildlifeSet.size() - 1;
        }
        return amount;
    }

    public int bearScoreCardA(Board currentUserBoard) {
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.BEAR_PLACED);
        return findGroupNumSize(currentUserBoard, wildlifePositions, 2);
    }

    public int bearScoreCardB(Board currentUserBoard) {
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.BEAR_PLACED);
        return findGroupNumSize(currentUserBoard, wildlifePositions, 3);
    }

    public int findGroupNumSize(Board currBoard, ArrayList<int[]> coOrdsToCheck, int wantedPairSize) {
        ArrayList<int[]> accountedList = new ArrayList<>();
        int numValidPairs = 0;
        for(int[] currCoOrd : coOrdsToCheck) {
            if(!Board.isCoOrdsContained(accountedList, currCoOrd)) {
                int pairSize = groupSizeHelper(accountedList, currCoOrd, currBoard);
                if(pairSize == wantedPairSize) {
                    numValidPairs++;
                }
            }
        }
        return numValidPairs;
    }


    public int groupSizeHelper(ArrayList<int[]> accountedForList , int[] currCoOrd, Board currBoard) {
        ArrayList<int[]> adjacent = getNeighbourTilesHelper(currBoard, currCoOrd);
        if(!Board.isCoOrdsContained(accountedForList, currCoOrd)) {
            accountedForList.add(currCoOrd);
        }
        Iterator<int[]> iterator = adjacent.iterator();
        while (iterator.hasNext()) {
            int[] adj = iterator.next();
            if (Board.isCoOrdsContained(accountedForList, adj) ||
                    !currBoard.getTile(adj[0], adj[1]).getPlacedToken().equals(currBoard.getTile(currCoOrd[0], currCoOrd[1]).getPlacedToken())) {
                iterator.remove();
            } else {
                accountedForList.add(adj);
            }
        }
        int members = 1;
        for(int[] adj : adjacent) {
            members += groupSizeHelper(accountedForList, adj, currBoard);
        }
        if(adjacent.isEmpty()) {
            return 1;
        } else {
            return members;
        }

    }

    public ArrayList<int[]> getArrayOfWildlifeHelper(Board userBoard, Wildlife wildlife) {
        ArrayList<int[]> wildlifeArray = new ArrayList<int[]>();
        for(int[] coord : userBoard.getOccupiedTileArray()) {
            if(userBoard.getTile(coord[0], coord[1]).getPlacedToken() == null) {
            } else if(userBoard.getTile(coord[0], coord[1]).getPlacedToken().equals(wildlife)) {
                wildlifeArray.add(coord);
            }
        }
        return wildlifeArray;
    }

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