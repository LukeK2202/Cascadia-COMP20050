import java.util.*;


// this class stores the tiles in the deck for the game
public class TileDeck extends Stack<Tile> {

    TileDeck() {
        super();

        //adds the necassary amount of tiles to a stack database called TileDeck, depending on the amount of players
        for (int i = 0; i < 25; i++) {
            super.add(new Tile(Tile.tileTypes.KEYSTONE));
        }

        for (int j = 0; j < 60; j++) {
            super.add(new Tile(Tile.tileTypes.RANDOM));
        }

        Collections.shuffle(this);

        for (int k = 0; k < ((4 - Casc.playerNum) * 20) + 2; k++) {
            super.remove(0);
        }
    }
}
