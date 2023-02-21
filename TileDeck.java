import java.util.Stack;


// this class stores the tiles in the deck for the game
public class TileDeck extends Stack<Tile>{

    TileDeck() {
        super();

        //adds the necassary amount of tiles to a stack database called TileDeck, depending on the amount of players
        for(int i = 0; i < ((20 * Casc.playerNum) + 3); i++) {

            super.add(
                    new Tile(Habitat.chooseHabitatArr(), Wildlife.chooseWildlifeArr())
            );
        }
    }
}
