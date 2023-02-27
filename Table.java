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

    public void cullWildlifeTokens() {
        /* 
         * If the wildlife shown tokens on the table are all of the same type, it discards them and takes new ones out the 
         * wildlife deck and fills the table array with them
         */
        long sameAmount = shownWildlife.stream().distinct().count();
        int occurences = Collections.frequency(shownWildlife, shownWildlife.get(0));
        if(sameAmount == 1) {
            for(int j = 0; j < 4; j++) {
                Wildlife temp = shownWildlife.get(j);
                wildlifeDeck.add(temp);
                shownWildlife.remove(j);
            }
            Collections.shuffle(wildlifeDeck);
            for (int i = 0; i < 4; i++) {
                shownWildlife.add(wildlifeDeck.pop());
            }
            if(shownWildlife.stream().distinct().count() == 1) {
                cullWildlifeTokens();
            }

        }
        else if (sameAmount == 2 && (occurences == 1 || occurences == 3)) {
            if(occurences == 1) {
                for(int i = 1; i < 3; i++) {
                    Wildlife temp = shownWildlife.get(i);
                    wildlifeDeck.add(temp);
                    shownWildlife.remove(i);
                }
            }
            else if(occurences == 3) {
                Wildlife tempHolderForToken = shownWildlife.get(0);
                for(int i = 0; i < 4; i++) {
                    if(tempHolderForToken.equals(shownWildlife.get(i))) {
                        Wildlife temp = shownWildlife.get(i);
                        wildlifeDeck.add(temp);
                        shownWildlife.remove(i);
                    }
                }
            }
            Collections.shuffle(wildlifeDeck);
            for(int i = 0; i < 3; i++) {
                shownWildlife.add(wildlifeDeck.pop());
            }
        }
        else {
            return;
        }
    }


}
