import java.util.Stack;


// this class stores the tiles in the deck for the game
public class TileDeck extends Stack<Tile>{

    TileDeck() {
        super();
        for(int i = 0; i < ((20 * Casc.playerNum) + 3); i++) {

            super.add(
                    new Tile(Habitat.chooseHabitatArr(), Wildlife.chooseWildlifeArr())
            );
        }
    }
}
