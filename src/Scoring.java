import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * Scoring class to calculate score of the user's board based on the scorecards selected
 */
public class Scoring {

    private Method selectedHawkCard;
    private Method selectedElkCard;
    private Method selectedFoxCard;
    private Method selectedBearCard;
    private Method selectedSalmonCard;


    public Scoring() {
        View view = new View();
        ArrayList<Method> hawkScoreCards = new ArrayList<>();
        ArrayList<Method> elkScoreCards = new ArrayList<>();
        ArrayList<Method> foxScoreCards = new ArrayList<>();
        ArrayList<Method> bearScoreCards = new ArrayList<>();
        ArrayList<Method> salmonScoreCards = new ArrayList<>();
        for(Method method : Scoring.class.getDeclaredMethods()) {
            if(method.getName().startsWith("hawkScoreCard")) hawkScoreCards.add(method);
            if(method.getName().startsWith("elkScoreCard")) elkScoreCards.add(method);
            if(method.getName().startsWith("foxScoreCard")) foxScoreCards.add(method);
            if(method.getName().startsWith("bearScoreCard")) bearScoreCards.add(method);
            if(method.getName().startsWith("salmonScoreCard")) salmonScoreCards.add(method);
        }
        hawkScoreCards.sort((name1, name2) -> name1.getName().compareTo(name2.getName()));
        elkScoreCards.sort((name1, name2) -> name1.getName().compareTo(name2.getName()));
        foxScoreCards.sort((name1, name2) -> name1.getName().compareTo(name2.getName()));
        bearScoreCards.sort((name1, name2) -> name1.getName().compareTo(name2.getName()));
        salmonScoreCards.sort((name1, name2) -> name1.getName().compareTo(name2.getName()));


        System.out.println("Current Available score cards: ");
        System.out.print("Hawk Cards -> ");
        for(int i = 0; i < hawkScoreCards.size(); i++) System.out.print(hawkScoreCards.get(i).getName() + "(" + (i + 1) + "), "); System.out.println();
        System.out.print("Elk Cards -> ");
        for(int i = 0; i < elkScoreCards.size(); i++) System.out.print(elkScoreCards.get(i).getName() + "(" + (i + 1) + "), "); System.out.println();
        System.out.print("Fox Cards -> ");
        for(int i = 0; i < foxScoreCards.size(); i++) System.out.print(foxScoreCards.get(i).getName() + "(" + (i + 1) + "), "); System.out.println();
        System.out.print("Bear Cards -> ");
        for(int i = 0; i < bearScoreCards.size(); i++) System.out.print(bearScoreCards.get(i).getName() + "(" + (i + 1) + "), "); System.out.println();
        System.out.print("Salmon Cards -> ");
        for(int i = 0; i < salmonScoreCards.size(); i++) System.out.print(salmonScoreCards.get(i).getName() + "(" + (i + 1) + "), "); System.out.println();

        System.out.println("Please select which hawk card you would like to use.");
        int input = view.getUserint(1, hawkScoreCards.size());
        selectedHawkCard = hawkScoreCards.get(input - 1);

        System.out.println("Please select which elk card you would like to use.");
        input = view.getUserint(1, elkScoreCards.size());
        selectedElkCard = elkScoreCards.get(input - 1);

        System.out.println("Please select which fox card you would like to use.");
        input = view.getUserint(1, foxScoreCards.size());
        selectedFoxCard = foxScoreCards.get(input - 1);

        System.out.println("Please select which bear card you would like to use.");
        input = view.getUserint(1, bearScoreCards.size());
        selectedBearCard = bearScoreCards.get(input - 1);

        System.out.println("Please select which salmon card you would like to use.");
        input = view.getUserint(1, salmonScoreCards.size());
        selectedSalmonCard = salmonScoreCards.get(input - 1);
    }

    public Scoring(boolean blank) {

    }

    public void generateScore(User currUser) {
        try {
            currUser.addScore((int) selectedHawkCard.invoke(this, currUser.getBoard()));
            currUser.addScore((int) selectedElkCard.invoke(this, currUser.getBoard()));
            currUser.addScore((int) selectedFoxCard.invoke(this, currUser.getBoard()));
            currUser.addScore((int) selectedBearCard.invoke(this, currUser.getBoard()));
            currUser.addScore((int) selectedSalmonCard.invoke(this, currUser.getBoard()));
            currUser.addScore(currUser.getNatureTokens());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


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

    public int hawkScoreCardB(Board currUserBoard) {
        return 0;
    }

    public int hawkScoreCardC(Board currUserBoard) {
        return 0;
    }

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
            if(wildlifeSet.size() > 5) amount += 5;
            else amount += wildlifeSet.size();
        }
        return amount;
    }

    public int foxScoreCardB(Board currUserBoard) {
        return 0;
    }

    public int foxScoreCardC(Board currUserBoard) {
        return 0;
    }

    public int bearScoreCardA(Board currentUserBoard) {
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.BEAR_PLACED);
        int numValidGroups = findGroupNumSize(currentUserBoard, wildlifePositions, 2);
        if(numValidGroups == 1) {
            return 4;
        } else if(numValidGroups == 2) {
            return 11;
        } else if(numValidGroups == 3) {
            return 19;
        } else if(numValidGroups == 4) {
            return 27;
        } else {
            return 0;
        }
    }

    public int bearScoreCardB(Board currentUserBoard) {
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.BEAR_PLACED);
        return findGroupNumSize(currentUserBoard, wildlifePositions, 3);
    }

    public int bearScoreCardC(Board currUserBoard) {
        return 0;
    }

    public int elkScoreCardA(Board currentUserBoard) {
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.ELK_PLACED);

        for(int[] elkCoord : wildlifePositions) {
        }
        return 0;
    }

    /**
     * Generates points for total amount of elks in their groups
     * @param currentUserBoard is the current user's board that will be used to navigate the tiles
     * @return returns amount of points based on the total amount of elf and their respective group sizes
     */
    public int elkScoreCardB(Board currentUserBoard) {
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.ELK_PLACED);
        ArrayList<Integer> groupSizes = getGroupSizeAmount(currentUserBoard, wildlifePositions);
        int totalScore = 0;

        for(Integer amount : groupSizes) {
            if (amount == 0) totalScore += 0;
            else if (amount <= 2) totalScore += (2 + (2 * (amount - 1)));
            else if (amount <= 4) totalScore += (7 + (3 * (amount - 3)));
            else if (amount <= 6) totalScore += (14 + (4 * (amount - 5)));
            else if (amount <= 8) totalScore += (23 + (5 * (amount - 7)));
            else totalScore += 28;
            }
        return totalScore;
        }

    public int elkScoreCardC(Board currUserBoard) {
        return 0;
    }

    public int salmonScoreCardA(Board currentUserBoard) {
        ArrayList<int[]> wildlifePositions = getArrayOfWildlifeHelper(currentUserBoard, Wildlife.SALMON_PLACED);
        return 1;
    }

    public int salmonScoreCardB(Board currentUserBoard) {
        int amount = 0;
        return amount;
    }

    public int salmonScoreCardC(Board currUserBoard) {
        return 0;
    }

    public ArrayList<Integer> getGroupSizeAmount(Board currBoard, ArrayList<int[]> coOrdsToCheck) {
            ArrayList<int[]> accountedList = new ArrayList<>();
            ArrayList<Integer> groupSize = new ArrayList<Integer>();
            for (int[] currCoOrd : coOrdsToCheck) {
                if (!Board.isCoOrdsContained(accountedList, currCoOrd)) {
                    groupSize.add(groupSizeHelper(accountedList, currCoOrd, currBoard));
                }
            }
            return groupSize;
    }

    /**
     * Finds either group amount or group size
     * @param currBoard is the current user's board that will be used to navigate the tiles
     * @param coOrdsToCheck The ArrayList of the coords of a specific wildlife that needs to be checked for groups
     *                      or group size
     * @param wantedPairSize the wanted size of the group, if the value is 99, initiates to search just for the
     *                      amount of wildlife within that group
     * @return Amount of groups of a specific size, or, the amount of a wildlife in a group if
     * param wantedPairSize is 99
     */
    public int findGroupNumSize(Board currBoard, ArrayList<int[]> coOrdsToCheck, int wantedPairSize) {
        if(wantedPairSize == 99) {
            ArrayList<int[]> accountedList = new ArrayList<>();
            int groupSize = 0;
            for (int[] currCoOrd : coOrdsToCheck) {
                if (!Board.isCoOrdsContained(accountedList, currCoOrd)) {
                    groupSize += groupSizeHelper(accountedList, currCoOrd, currBoard);
                }
            }
            return groupSize;
        }
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

//    public int longestLineInARow() {
//
//    }

    /**
     * assists findGroupNumSize method in finding the amount of wildlife adjacent to each other
     * @param accountedForList is the ArrayList of the wildlife already accounted for, meaning,
     *                         they do not need to be added again
     * @param currCoOrd used to find the neighbours of the current coord and only adds those neighbours if they are not
     *                  in the accountForList ArrayList and / or if their wildlife token does not match the currCoord token
     * @param currBoard is the current user's board that will be used to navigate the tiles
     * @return returns amount of wildlife within this adjacent group
     */
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