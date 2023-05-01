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

        int pickedNum = rn.nextInt(0, placeableArr.size());
        return new Command("P" + pickedNum);
    }

    // take in a random boolean for an optional cull
    public boolean optionalCullInput(){
        return rn.nextBoolean();
    }

     public boolean botYorN(Board botBoard, Wildlife selectedWildlife) {
         for(int[] coOrd : botBoard.getOccupiedTileArray()) {

             if(!botBoard.getTile(coOrd[0], coOrd[1]).hasPlacedToken()) {
                 String tokenToCheck = selectedWildlife.getName();
                 ArrayList<Wildlife> animals = botBoard.getTile(coOrd[0], coOrd[1]).getAnimals();

                 for(Wildlife animal : animals) {
                     if(animal.getName().equals(tokenToCheck)){
                         return true;
                     }
                 }
             }
         }
         return false;
     }

     public Command placeWildlife(Board botBoard, Wildlife selectedWildlife) {
         ArrayList<Integer> placements = new ArrayList<Integer>();
         for(int[] coOrd : botBoard.getOccupiedTileArray()) {
             if(!botBoard.getTile(coOrd[0], coOrd[1]).hasPlacedToken()) {
                 ArrayList<Wildlife> animals = botBoard.getTile(coOrd[0], coOrd[1]).getAnimals();
                 for(Wildlife animal : animals){
                     if(animal.getName().equals(selectedWildlife.getName())) {
                         placements.add(botBoard.getTile(coOrd[0], coOrd[1]).getCoOrd());
                     }
                 }
             }
         }

         int pickedTile = rn.nextInt(placements.size());

         return new Command("P" + placements.get(pickedTile));
     }

}
