import java.util.ArrayList;
import java.util.Collections;
import Exceptions.CantPlaceWildlifeException;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

public class WildlifeLogic {

    /**
     * All possible tile  locations on which the wildlife can be placed on
     * @param wildlife the wildlife token we are working with
     * @param currBoard the board of the current bot
     * @return ArrayList of all possible locations
     */
    public ArrayList<int[]> possibleLocationsForWildlife(Wildlife wildlife, Board currBoard) {
        //get all locations that a wildlife can be placed on
        ArrayList<int[]> wildlifeLocations = new ArrayList<int[]>();
        for(int[] i : currBoard.getOccupiedTileArray()) {
            if(!currBoard.getTile(i[0], i[1]).hasPlacedToken()) {
                for(Wildlife animal : currBoard.getTile(i[0], i[1]).getAnimals()) {
                    if(animal.getName().equals(wildlife.getName())) {
                        wildlifeLocations.add(i);
                    }
                }
            }
        }
        return wildlifeLocations;
    }

    /**
     * Gets location which gets the highest points on the board
     * @param wildlifeLocations ArrayList of all possible locations for token to be placed
     * @param wildlife wildlife token we are working with
     * @param bot the current bot that holds the board and the tokens
     * @return int[] co-ordinates of tile
     */
    public int[] wildlifePointPlacementLogic(ArrayList<int[]> wildlifeLocations, Wildlife wildlife, final Bot bot) {
        int[] scoreValues = new int[wildlifeLocations.size()];

        //try to see where wildlife will yield most points
        for(int i = 0; i < wildlifeLocations.size(); i++) {
            scoreValues[i] = bestScoreForLocation(bot, wildlifeLocations.get(i), wildlife);
        }

        //get the best wildlife location
        int maxIndex = 0;
        for(int k = 0; k < scoreValues.length; k++) {
            if(scoreValues[k] > scoreValues[maxIndex] || (scoreValues[k] == scoreValues[maxIndex] && bot.getBoard().getTile(wildlifeLocations.get(k)[0], wildlifeLocations.get(k)[1]).isKeystoneTile())) {
                maxIndex = k;
            }
        }
        if(scoreValues[maxIndex] < 0) {
            return new int[]{-1, -1};
        }
        return wildlifeLocations.get(maxIndex);
    }

    /**
     * gets new score for the token placed on a tile on the board
     * @param bot current bot whose turn it is
     * @param wildlifePosition int[] location of the tile
     * @param wildlife wildlife token that is being placed
     * @return the new score of the bot if the token is placed on that position
     */
    public int bestScoreForLocation(Bot bot, int[] wildlifePosition, Wildlife wildlife) {
        Scoring scoreBoard = new Scoring(true);
        int scoreValues = -1;
        int currentBotScore = bot.getScore();

        int totalNewBoardScore = 0;
        Board newBoard = getBoardCopy(bot.getBoard());

        try {
            newBoard.getTile(wildlifePosition[0], wildlifePosition[1]).addWildlifeToken(wildlife);
        } catch (CantPlaceWildlifeException ex) {
            System.out.println(ex.getMessage());
        }
        totalNewBoardScore += scoreBoard.hawkScoreCardA(newBoard);
        totalNewBoardScore += scoreBoard.bearScoreCardA(newBoard);
        totalNewBoardScore += scoreBoard.elkScoreCardA(newBoard);
        totalNewBoardScore += scoreBoard.foxScoreCardA(newBoard);
        totalNewBoardScore += scoreBoard.salmonScoreCardA(newBoard);

        if(newBoard.getTile(wildlifePosition[0], wildlifePosition[1]).isKeystoneTile()) totalNewBoardScore += 2;

        if(wildlife.getName().equals("Bear")) {
            ArrayList<int[]> bearNeighbours = scoreBoard.getNeighbourTilesHelper(newBoard, wildlifePosition);
            for(int[] tile : bearNeighbours) {
                if(Collections.frequency(newBoard.getTile(tile[0], tile[1]).getAnimals(), wildlife) > 0){
                    totalNewBoardScore += 2;
                }
            }
        }

        if(totalNewBoardScore > currentBotScore) {
            scoreValues = totalNewBoardScore - currentBotScore;
        }
        return scoreValues;
    }

    /**
     * Creates an exact copy of the bots board, but in a different memory location, made not to modify bots current board
     * @param ogBoard original board that the current bot holds
     * @return copy of the original board
     */
    public Board getBoardCopy(Board ogBoard) {
        Board newBoard = new Board(true);
        for(int[] t : ogBoard.getOccupiedTileArray()) {
            Tile tile = ogBoard.getTile(t[0], t[1]);
            Tile copyTile = new Tile(tile);
            newBoard.addTile(copyTile, t[0], t[1]);
        }
        return newBoard;
    }
}
