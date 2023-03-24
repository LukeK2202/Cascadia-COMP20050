import Exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTests {



    @Test
    public void testBearA() {
        /*
        Bear Card A gives points for each pair of exactly 2 bears, more points for each pair.
        1 pair -> 4 points
        2 pairs -> 11 points
        3 pairs -> 19 points
        4 pairs -> 27 points
         */
        Board board = new Board(true);
        Scoring scoreBoard = new Scoring();
        TileCreators tc = new TileCreators();

        /*
        Board Visualisation:
        10,10<     >10,11<     >10,12<     >10,13
	          11,10<     >11,11<     >11,12
        12,10<     >12,11<     >12,12<     >12,13
	          13,10<     >13,11<     >13,12
        14,10<     >14,11<     >14,12<     >14,13
         */

        //Creating Bear Tiles and some other animal tile
        Tile tB = tc.createPlacedBearTile();
        Tile tH = tc.createPlacedHawkTile();

        //Creating valid 2 bear pair
        board.addTile(tB, 11, 11);
        board.addTile(tB, 11, 12);
        //Creating valid 2 bear pair, with a neighbouring other animal
        board.addTile(tB, 12, 10);
        board.addTile(tB, 12, 9);
        board.addTile(tH, 13, 10);
        //Creating invalid 3 bear pair
        board.addTile(tB, 14, 12);
        board.addTile(tB, 14, 13);
        board.addTile(tB, 15, 12);

        assertEquals(11, scoreBoard.bearScoreCardA(board));
    }

    @Test
    public void testFoxA() {
        /*
        Fox card A gives points for each unique wildlife bordering any fox
        1 point for each unique wildlife bordering it
         */
        Board board = new Board(true);
        Scoring scoreBoard = new Scoring();
        TileCreators tc = new TileCreators();

        /*
        Board Visualisation:
        10,10<     >10,11<     >10,12<     >10,13
	          11,10<     >11,11<     >11,12
        12,10<     >12,11<     >12,12<     >12,13
	          13,10<     >13,11<     >13,12
        14,10<     >14,11<     >14,12<     >14,13
         */

        //Creating sample tiles
        Tile tB = tc.createPlacedBearTile();
        Tile tH = tc.createPlacedHawkTile();
        Tile tE = tc.createPlacedElkTile();
        Tile tF = tc.createPlacedFoxTile();
        Tile tS = tc.createPlacedSalmonTile();

        //Creating a fox with two unique neighbours
        board.addTile(tF, 11, 11);
        board.addTile(tH, 11, 12);
        board.addTile(tE, 12, 11);
        assertEquals(2, scoreBoard.foxScoreCardA(board));

        board.addTile(tB, 10, 12);
        board.addTile(tF, 11, 10);
        assertEquals(4, scoreBoard.foxScoreCardA(board));

        board.addTile(tE, 12, 12);
        assertEquals(4, scoreBoard.foxScoreCardA(board));
    }
}
