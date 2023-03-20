import java.util.ArrayList;
import java.util.Collections;

public class Table {
    //The tile deck to hold all tiles in a random stack
    private TileDeck tileDeck;
    //The wildlife deck to hold all wildlife in a random stack
    private WildlifeDeck wildlifeDeck;

    //Arraylist to hold the 4 displayed tiles
    private ArrayList<Tile> shownTiles;
    //Arraylist to hold the 4 displayed wildlife
    private ArrayList<Wildlife> shownWildlife;

    private Tile selectedTile;
    private Wildlife selectedWildlife;

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

    //return the shown wildlife at desired index
    public Wildlife getWildlife(int n) {
        return shownWildlife.get(n);
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
    public void addTile(int position) {
        shownTiles.add(position, tileDeck.pop());
    }
    //adds a wildlife back to shown wildlife from the stack
    public void addWildlife(int position) {
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
    public void selectColumn(int n) {
        this.selectedTile = shownTiles.get(n - 1);
        this.selectedWildlife = shownWildlife.get(n - 1);

        shownTiles.remove(n - 1);
        shownWildlife.remove(n - 1);

        drawFromDecks(n - 1);
    }

    public void unselectTile() {
        this.selectedTile = null;
    }

    public void unselectWildlife() {
        this.selectedWildlife = null;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public Wildlife getSelectedWildlife() {
        return selectedWildlife;
    }

    public boolean hadSelectedTile() {
        return selectedTile != null;
    }

    public boolean hadSelectedWildlife() {
        return selectedWildlife != null;
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
            addWildlife(n);
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
