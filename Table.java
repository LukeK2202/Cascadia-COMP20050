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
    //removes a tile from the shown tiles
    public Tile takeTile(int position) {
        return shownTiles.remove(position);
    }
    //removes the wildlife from the shown wildlife
    public Wildlife takeWildlife(int position) {
        return shownWildlife.remove(position);
    }
    //adds a tile back to shown tiles from the stack
    public void addTile() {
        shownTiles.add(tileDeck.pop());
    }
    //adds a wildlife back to shown wildlife from the stack
    public void addWildlife() {
        shownWildlife.add(wildlifeDeck.pop());
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

    public void unselect() {
        this.selectedTile = null;
        this.selectedWildlife = null;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public Wildlife getSelectedWildlife() {
        return selectedWildlife;
    }

    public boolean hadSelected() {
        return selectedTile != null;
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

    public void cullAllCall() {
        for(int i = 0; i < 4; i++) {
            wildlifeDeck.add(shownWildlife.get(3 - i));
            shownWildlife.remove(3 - i);
        }
        Collections.shuffle(wildlifeDeck);
        for(int i = 0; i < 4; i++) {
            shownWildlife.add(wildlifeDeck.pop());
        }
    }


}
