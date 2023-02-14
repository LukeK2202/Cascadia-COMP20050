import java.util.ArrayList;
import java.util.Arrays;

public class Casc {
    //Main java File, will use this to start the game.

    public static void main(String[] args) {
        View view = new View();
        Deck deck = new Deck();
        Board board = new Board();
        WildlifeDeck wDeck = new WildlifeDeck();

        view.printBoard(board);
    }
}
