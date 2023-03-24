
import Exceptions.CantPlaceWildlifeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTests {

    @Test
    public void testBearA() {
        Board board = new Board(true);
        Scoring scoreBoard = new Scoring();

        Tile t1;
        do {
            t1 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t1.getAnimals().contains(Wildlife.BEAR));
        try {
            t1.addWildlifeToken(Wildlife.BEAR_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldnt Place bear token");
        }
        board.addTile(t1, 11, 11);

        Tile t2;
        do {
            t2 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t2.getAnimals().contains(Wildlife.BEAR));
        try {
            t2.addWildlifeToken(Wildlife.BEAR_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldnt Place bear token");
        }
        board.addTile(t2, 11, 12);

        Tile t3;
        do {
            t3 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t3.getAnimals().contains(Wildlife.BEAR));
        try {
            t3.addWildlifeToken(Wildlife.BEAR_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldnt Place bear token");
        }
        board.addTile(t3, 3, 3);

        Tile t4;
        do {
            t4 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t4.getAnimals().contains(Wildlife.FOX));
        try {
            t4.addWildlifeToken(Wildlife.FOX_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldnt Place token");
        }
        board.addTile(t4, 4, 3);

        Tile t5;
        do {
            t5 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t5.getAnimals().contains(Wildlife.BEAR));
        try {
            t5.addWildlifeToken(Wildlife.BEAR_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldnt Place bear token");
        }
        board.addTile(t5, 4, 4);

        Tile t6;
        do {
            t6 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t6.getAnimals().contains(Wildlife.BEAR));
        try {
            t6.addWildlifeToken(Wildlife.BEAR_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldnt Place bear token");
        }
        board.addTile(t5, 3, 4);


        assertEquals(1, scoreBoard.bearScoreCardA(board));
        assertEquals(1, scoreBoard.bearScoreCardB(board));

    }
}
