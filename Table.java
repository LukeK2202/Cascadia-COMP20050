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
            shownWildlife.add(wildlifeDeck.pop());
            shownTiles.add(tileDeck.pop());
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

}
