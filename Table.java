import java.util.ArrayList;
import java.util.Collections;

public class Table {
    private TileDeck tileDeck;
    private WildlifeDeck wildlifeDeck;

    private ArrayList<Tile> shownTiles;
    private ArrayList<Wildlife> shownWildlife;

    Table() {
        tileDeck = new TileDeck();
        Collections.shuffle(tileDeck);
        wildlifeDeck = new WildlifeDeck();
        Collections.shuffle(wildlifeDeck);
        shownTiles = new ArrayList<>();
        shownWildlife = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            shownWildlife.add(wildlifeDeck.pop());
            shownTiles.add(tileDeck.pop());
        }
    }

    public Tile getTile(int n) {
        return shownTiles.get(n);
    }

    public Wildlife getWildlife(int n) {
        return shownWildlife.get(n);
    }

}
