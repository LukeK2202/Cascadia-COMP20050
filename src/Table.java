import java.util.ArrayList;
import java.util.Collections;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

public class Table {
    //The tile deck to hold all tiles in a random stack
    private TileDeck tileDeck;
    //The wildlife deck to hold all wildlife in a random stack
    private WildlifeDeck wildlifeDeck;

    //Arraylist to hold the 4 displayed tiles
    private ArrayList<Tile> shownTiles;
    //Arraylist to hold the 4 displayed wildlife
    private ArrayList<Wildlife> shownWildlife;

    private Tile selectedTile = new Tile(Tile.tileTypes.BLANK);
    private Wildlife selectedWildlife = Wildlife.BLANK_I_NATOR;

    //Table constructor
    Table() {
        //Created and shuffles a tile deck
        tileDeck = new TileDeck();
        Collections.shuffle(tileDeck);
        //Created and shuffles a wildlife deck
        wildlifeDeck = new WildlifeDeck();
        Collections.shuffle(wildlifeDeck);
        //Initializes the two arraylists
        shownTiles = new ArrayList<>();
        shownWildlife = new ArrayList<>();

        //adds 4 wildlife and tiles to the arraylists
        for(int i = 0; i < 4; i++) {
            drawFromDecks(i);
        }
    }

    //return the shown tile at desired index
    public Tile getTile(int n) {
        return shownTiles.get(n);
    }

    public ArrayList<Tile> getShownTiles() {
        return shownTiles;
    }

    //return the shown wildlife at desired index
    public Wildlife getWildlife(int n) {
        return shownWildlife.get(n);
    }

    public ArrayList<Wildlife> getShownWildlife() {
        return shownWildlife;
    }

    //removes a tile from the shown tiles and returns to deck
    public void returnTile(int position) {
        tileDeck.add(getTile(position));
        removeTile(position);
    }
    //removes the wildlife from the shown wildlife and returns to deck
    public void returnWildlife(int position) {
        wildlifeDeck.add(getWildlife(position));
        removeWildlife(position);
    }
    //adds a tile back to shown tiles from the stack
    public void drawTile(int position) {
        shownTiles.add(position, tileDeck.pop());
    }
    //adds a wildlife back to shown wildlife from the stack
    public void drawWildlife(int position) {
        shownWildlife.add(position, wildlifeDeck.pop());
    }

    public void removeTile(int position) {
        shownTiles.remove(position);
    }

    public void removeWildlife(int position) {
        shownWildlife.remove(position);
    }
    public boolean deckIsEmpty() {
        return tileDeck.isEmpty();
    }

    public void drawFromDecks(int n) {
        shownWildlife.add(n, wildlifeDeck.pop());
        shownTiles.add(n, tileDeck.pop());
    }

    public void selectTile(int n) {
        Tile tmpTile = selectedTile;

        this.selectedTile = shownTiles.get(n - 1);
        shownTiles.remove(n - 1);
        shownTiles.add(n - 1, tmpTile);
    }

    public void selectWildlife(int n) {
        Wildlife tmpWildlife = selectedWildlife;

        this.selectedWildlife = shownWildlife.get(n - 1);
        shownWildlife.remove(n - 1);
        shownWildlife.add(n - 1, tmpWildlife);
    }

    public void fillTable() {
        for(int i = 0; i < 4; i++) {
            if(shownWildlife.get(i) == Wildlife.BLANK_I_NATOR) {
                removeWildlife(i);
                drawWildlife(i);
            }
            if(shownTiles.get(i).isBlank()) {
                removeTile(i);
                drawTile(i);
            }
        }
    }

    public void SelectTileWild(int n) {
        selectTile(n);
        selectWildlife(n);
    }

    public void unselectTile() {
        this.selectedTile = new Tile(Tile.tileTypes.BLANK);
    }

    public void unselectWildlife() {
        this.selectedWildlife = Wildlife.BLANK_I_NATOR;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public Wildlife getSelectedWildlife() {
        return selectedWildlife;
    }

    public boolean hadSelectedTile() {
        return !selectedTile.isBlank();
    }

    public boolean hadSelectedWildlife() {
        return selectedWildlife != Wildlife.BLANK_I_NATOR;
    }

    //will return 0 if a cull is not required, return 1 if an ALL cull is needed,
    //and 2 if an optional cull can be done
    public int cullDetectionMethod() {
        int occurences = 0;
        for(int i = 0; i < 2; i++) {
            occurences = Collections.frequency(shownWildlife, shownWildlife.get(i));
            if(occurences >= 3) {
                break;
            }
        }
        if(occurences == 4) {
            return 2;
        }
        else if(occurences == 3){
            return 1;
        }
        return 0;
    }

    //culls all the wildlife coressponding to the indexes given to the function
    private void cull(int ... i) {
        for(int n : i) {
            returnWildlife(n);
            Collections.shuffle(wildlifeDeck);
            drawWildlife(n);
        }
    }

    //culls every index within the shownwildlife array
    public void cullAllCall() {
        cull(0, 1, 2, 3);
    }


    //cull that detects which wildlife repeats three times within the array, then those indexes are called to the cull method
    public void optionalCull() {
        int occurences = 0;
        Wildlife wildCopy = null;

        for(int j = 0; j < 2; j++) {
            occurences = Collections.frequency(shownWildlife, shownWildlife.get(j));
            if(occurences == 3) {
                wildCopy = shownWildlife.get(j);
                break;
            }
        }
        if(wildCopy != null) {
            for(int i = 0; i < shownWildlife.size(); i++) {
                if(shownWildlife.get(i) == wildCopy) {
                    cull(i);
                }
            }
        }
    }

    //calls cull on the array of the chosen indexes by the user
    public void natureTokenCull(int[] positions) {
        cull(positions);
    }
}
