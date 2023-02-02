import java.util.Stack;

public class Deck extends Stack<Tile> {

    Deck() {
        super();
        for(int i = 0; i < 43; i++) {

            super.add(
                    new Tile(Habitat.chooseHabitatArr(), Wildlife.chooseWildlifeArr())
            );
        }
    }
}
