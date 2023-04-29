import java.util.ArrayList;

import Exceptions.CantPlaceWildlifeException;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

public class PlacementLogic {


    // boolean hasHawk = false;
    // ArrayList<int[]> hawkLocations = new ArrayList<int[]>();
    // for(Wildlife w : table.getShownWildlife()) {
    //     if(w.getName().equals(Wildlife.HAWK.getName())) {
    //         hasHawk = true;
    //         break;
    //     }
    // }
    // if(!hasHawk) {
    //     return null;
    // }

    public int[] hawkLogic(Table table, final Bot bot) {
        //get all locations that a hawk can be placed on
        ArrayList<int[]> hawkLocations = new ArrayList<int[]>();
        Scoring scoreBoard = new Scoring(true);
        for(int[] i : bot.getBoard().getOccupiedTileArray()) {
            if(!bot.getBoard().getTile(i[0], i[1]).hasPlacedToken()) {
                for(Wildlife animal : bot.getBoard().getTile(i[0], i[1]).getAnimals()) {
                    if(animal.getName().equals(Wildlife.HAWK.getName())) {
                        hawkLocations.add(i);
                    }
                }
            }
        }
        int[] scoreValues = new int[hawkLocations.size()];
        int currentBotScore = bot.getScore();

        //try to see where hawk is best
        for(int j = 0; j < hawkLocations.size(); j++) {
            int[] hawkPosition = hawkLocations.get(j);
            Bot botInstance = new Bot(bot, j);

            try {
                botInstance.getBoard().getTile(hawkPosition[0], hawkPosition[1]).addWildlifeToken(Wildlife.HAWK_PLACED);
            } catch (CantPlaceWildlifeException ex) {
                System.out.println(ex.getMessage());
            }

            if(scoreBoard.hawkScoreCardA(botInstance.getBoard()) > currentBotScore) {
                scoreValues[j] = scoreBoard.hawkScoreCardA(botInstance.getBoard());
            }
        }

        //get best hawk location
        int minIndex = 0;
        for(int k = 1; k < scoreValues.length; k++) {
            if(scoreValues[k] > scoreValues[minIndex]) {
                minIndex = k;
            }
        }
        if(scoreValues[minIndex] <= 0) {
            return null;
        }
        return hawkLocations.get(minIndex);

    }
}
