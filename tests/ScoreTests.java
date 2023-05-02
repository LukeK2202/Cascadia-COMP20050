import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

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
        Scoring scoreBoard = new Scoring(true);
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
    public void testBearB() {
        /*
        Bear Card B gives points for each pair of exactly 3 bears.
        10 points for each pair
         */
        Board board = new Board(true);
        Scoring scoreBoard = new Scoring(true);
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

        //Creating invalid 2 bear pair
        board.addTile(tB, 11, 11);
        board.addTile(tB, 11, 12);
        assertEquals(0, scoreBoard.bearScoreCardB(board));
        //Creating valid 3 bear pair
        board.addTile(tB, 12, 10);
        board.addTile(tB, 12, 9);
        board.addTile(tB, 13, 10);
        assertEquals(10, scoreBoard.bearScoreCardB(board));
        //Creating valid 3 bear pair, with neighbour of other animal
        board.addTile(tB, 14, 12);
        board.addTile(tB, 14, 13);
        board.addTile(tB, 15, 12);
        board.addTile(tH, 15, 13);
        assertEquals(20, scoreBoard.bearScoreCardB(board));
    }

    @Test
    public void testBearC() {
        /*
        Bear Card C gives points for each pair of groups between size of 1 and 3.
        2 points for size 1.
        5 points for size 2.
        8 points for size 3.
        bonus 3 points if you have atleast 1 of each size
         */
        Board board = new Board(true);
        Scoring scoreBoard = new Scoring(true);
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

        //Creating valid 1 bear pair
        board.addTile(tB, 10, 10);
        assertEquals(2, scoreBoard.bearScoreCardC(board));

        //Creating valid 2 bear pair
        board.addTile(tB, 11, 11);
        board.addTile(tB, 11, 12);
        assertEquals(7, scoreBoard.bearScoreCardC(board));
        //Adding 1 bear to become a pair of 3
        board.addTile(tB, 10, 12);
        assertEquals(10, scoreBoard.bearScoreCardC(board));

        //Creating valid 2 bear pair, which then gives the bonus 3 points
        board.addTile(tB, 12, 10);
        board.addTile(tB, 13, 10);
        assertEquals(18, scoreBoard.bearScoreCardC(board));
    }

    @Test
    public void testFoxA() {
        /*
        Fox card A gives points for each unique wildlife bordering any fox
        1 point for each unique wildlife bordering it
         */
        Board board = new Board(true);
        Scoring scoreBoard = new Scoring(true);
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

        //Creating a fox with two unique neighbours
        board.addTile(tF, 11, 11);
        board.addTile(tH, 11, 12);
        board.addTile(tE, 12, 11);
        assertEquals(2, scoreBoard.foxScoreCardA(board));

        board.addTile(tB, 10, 12);
        board.addTile(tF, 11, 10);
        assertEquals(6, scoreBoard.foxScoreCardA(board));

        board.addTile(tE, 12, 12);
        assertEquals(6, scoreBoard.foxScoreCardA(board));
    }

    @Test
    public void testElkA() {
                /*
        Elk card A gives points for each elk within a straight line, up to 4 per line maximum
                 */

        Board board = new Board(true);
        Scoring scoreBoard = new Scoring(true);
        TileCreators tc = new TileCreators();

        Tile tE = tc.createPlacedElkTile();

        /*
        Board Visualisation:
        10,10<     >10,11<     >10,12<     >10,13
	          11,10<     >11,11<     >11,12
        12,10<     >12,11<     >12,12<     >12,13
	          13,10<     >13,11<     >13,12
        14,10<     >14,11<     >14,12<     >14,13
         */

        //Creating elks and testing output

        board.addTile(tE, 14, 10);
        assertEquals(2, scoreBoard.elkScoreCardA(board));

        board.addTile(tE, 14,11);
        assertEquals(5, scoreBoard.elkScoreCardA(board));

        board.addTile(tE, 14, 12);

        assertEquals(9, scoreBoard.elkScoreCardA(board));

        board.addTile(tE, 13, 12);
        assertEquals(11, scoreBoard.elkScoreCardA(board));

        board.addTile(tE, 12, 13);
        assertEquals(14, scoreBoard.elkScoreCardA(board));

        board.addTile(tE, 12, 14);
        assertEquals(16, scoreBoard.elkScoreCardA(board));

        board.addTile(tE, 12, 15);
        assertEquals(19, scoreBoard.elkScoreCardA(board));

        board.addTile(tE, 15, 12);
        assertEquals(21, scoreBoard.elkScoreCardA(board));
    }

    @Test
    public void testElkB() {
                /*
        Elk card B gives points for each elk within a group size, in any shape
                 */

        Board board = new Board(true);
        Scoring scoreBoard = new Scoring(true);
        TileCreators tc = new TileCreators();

        Tile tB = tc.createPlacedBearTile();
        Tile tH = tc.createPlacedHawkTile();
        Tile tE = tc.createPlacedElkTile();
        Tile tF = tc.createPlacedFoxTile();

        /*
        Board Visualisation:
        10,10<     >10,11E<     >10,12E<     >10,13
	          11,10E<     >11,11E<     >11,12E
        12,10<     >12,11<     >12,12<     >12,13
	          13,10<     >13,11<     >13,12
        14,10E<     >14,11E<     >14,12E<     >14,13
         */

        //Creating elks and testing output
        board.addTile(tE, 11, 11);
        board.addTile(tE, 11, 12);

        assertEquals(4, scoreBoard.elkScoreCardB(board));

        //Adding more elks and other wildlife to see if they are ignored in scoring
        board.addTile(tE, 11, 10);
        board.addTile(tE, 10, 11);
        board.addTile(tE, 10, 12);
        board.addTile(tB, 12, 13);
        board.addTile(tH, 10, 13);

        assertEquals(14, scoreBoard.elkScoreCardB(board));

        //Further addition of elks and other wildlife
        board.addTile(tE, 14, 10);
        board.addTile(tE, 14, 11);
        board.addTile(tF, 13, 10);
        assertEquals(18, scoreBoard.elkScoreCardB(board));

        board.addTile(tE, 14, 12);
        assertEquals(21, scoreBoard.elkScoreCardB(board));
    }
    @Test
    public void testHawkA() {
        /*
        Hawk card A gives points for each hawk that are not adjacent to any other hawk
        1 hawk -> 2 points
        2 hawks -> 5 points
        3 hawks -> 8 points
        4 hawks -> 11 points
        5 hawks -> 14 points
        6 hawks -> 18 points
        7 hawks -> 22 points
        8+ hawks -> 26 points
         */
        Board board = new Board(true);
        Scoring scoreBoard = new Scoring(true);
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

        board.addTile(tH, 11, 11);
        board.addTile(tB, 11, 12);
        assertEquals(2, scoreBoard.hawkScoreCardA(board));

        board.addTile(tH, 14, 10);
        assertEquals(5, scoreBoard.hawkScoreCardA(board));

        board.addTile(tH, 10, 12);
        assertEquals(2, scoreBoard.hawkScoreCardA(board));
    }

    @Test
    public void testSalmonA(){
        /*
        Board Visualisation:
        10,10<     >10,11<     >10,12<     >10,13
	          11,10<     >11,11<     >11,12
        12,10<     >12,11<     >12,12<     >12,13
	          13,10<     >13,11<     >13,12
        14,10<     >14,11<     >14,12<     >14,13
         */

        Board board = new Board(true);
        Scoring scoring = new Scoring(true);
        TileCreators tc = new TileCreators();

        //Creating sample tiles
        Tile tB = tc.createPlacedBearTile();
        Tile tS = tc.createPlacedSalmonTile();

        board.addTile(tS, 11, 12);
        board.addTile(tS, 10, 12);
        board.addTile(tS, 10, 13);
        assertEquals(7, scoring.salmonScoreCardA(board));

        board.addTile(tS, 11, 13);
        assertEquals(0, scoring.salmonScoreCardA(board));

        board.addTile(tS, 14, 9);
        board.addTile(tS, 14, 10);
        board.addTile(tS, 13, 10);
        board.addTile(tS, 13, 11);
        board.addTile(tB, 14, 11);
        assertEquals(11, scoring.salmonScoreCardA(board));
    }

    @Test
    public void testSalmonB(){
        /*
        Board Visualisation:
        10,10<     >10,11<     >10,12<     >10,13
	          11,10<     >11,11<     >11,12
        12,10<     >12,11<     >12,12<     >12,13
	          13,10<     >13,11<     >13,12
        14,10<     >14,11<     >14,12<     >14,13
         */

        Board board = new Board(true);
        Scoring scoring = new Scoring(true);
        TileCreators tc = new TileCreators();

        //Creating sample tiles
        Tile tB = tc.createPlacedBearTile();
        Tile tS = tc.createPlacedSalmonTile();

        board.addTile(tS, 11, 12);
        board.addTile(tS, 10, 12);
        board.addTile(tS, 10, 13);
        assertEquals(8, scoring.salmonScoreCardB(board));

        board.addTile(tS, 11, 13);
        assertEquals(0, scoring.salmonScoreCardB(board));

        board.addTile(tS, 14, 9);
        board.addTile(tS, 14, 10);
        board.addTile(tS, 13, 10);
        board.addTile(tS, 13, 11);
        board.addTile(tB, 14, 11);
        assertEquals(12, scoring.salmonScoreCardB(board));
    }

    @Test
    public void testSalmonC(){
        /*
        Board Visualisation:
        10,10<     >10,11<     >10,12<     >10,13
	          11,10<     >11,11<     >11,12
        12,10<     >12,11<     >12,12<     >12,13
	          13,10<     >13,11<     >13,12
        14,10<     >14,11<     >14,12<     >14,13
         */

        Board board = new Board(true);
        Scoring scoring = new Scoring(true);
        TileCreators tc = new TileCreators();

        //Creating sample tiles
        Tile tB = tc.createPlacedBearTile();
        Tile tS = tc.createPlacedSalmonTile();

        board.addTile(tS, 11, 12);
        board.addTile(tS, 10, 12);
        board.addTile(tS, 10, 13);
        assertEquals(9, scoring.salmonScoreCardC(board));

        board.addTile(tS, 11, 13);
        assertEquals(0, scoring.salmonScoreCardC(board));

        board.addTile(tS, 14, 9);
        board.addTile(tS, 14, 10);
        board.addTile(tS, 13, 10);
        board.addTile(tS, 13, 11);
        board.addTile(tB, 14, 11);
        assertEquals(11, scoring.salmonScoreCardC(board));
    }
}
