import java.util.ArrayList;

//Main board class that will contain the tiles for each user
public class Board {

    //Main board matrix, a grid of tile classes
    private Tile[][] board = new Tile[21][21];
    //Arraylist to hold interger arrays of 2, acting as Co-ords, for placed tiles
    private ArrayList<int[]> occupiedTiles = new ArrayList<int[]>();
    //Arraylist to hold interger arrays of 2, acting as Co-ords, for places tiles can be placed
    private ArrayList<int[]> placeableArea = new ArrayList<int[]>();
    //Board constructor, adds 3 tiles as a started tile. 1 keystone and 2 randoms
    //Origin of board is 6,6.
    Board() {
        addTile(new Tile(Tile.tileTypes.KEYSTONE), 11, 11);
        addTile(new Tile(Tile.tileTypes.RANDOM), 12, 11);
        addTile(new Tile(Tile.tileTypes.RANDOM), 12, 12);
    }

    //Returns board length
    public int getBoardLength() {
        return board.length;
    }

    //Returns board width
    public int getBoardWidth() {
        return board[0].length;
    }

    //Method to check if a row contains any non initiated tiles
    public boolean isRowNull(int row) {
        for(int i = 0; i < board[row].length; i++) {
            if(board[row][i] == null) {
                return true;
            }
        }
        return false;
    }

    //Method to populate an entire row with blank tiles
    private void populateRow(int row) {
        for(int i = 0; i < board[row].length; i++) {
            board[row][i] = new Tile(Tile.tileTypes.BLANK);
        }
    }

    public Tile getTile(int row, int column) {
        return board[row][column];
    }

    //Method to add a tile to the board, requiring the row and column
    private void addTile(Tile tile ,int row, int column) {
        if(isRowNull(row)) {
            populateRow(row);
        }
        if(row > 0) {
            if(isRowNull(row - 1)) {
                populateRow(row - 1);
            }
        }
        if(row < getBoardLength()) {
            if(isRowNull(row + 1)) {
                populateRow(row + 1);
            }
        }
        int coOrd[] = {row, column};
        occupiedTiles.add(coOrd);
        board[row][column] = tile;
        tile.setCoOrd(occupiedTiles.indexOf(coOrd));
    }

    public void addPlaceableArea(int row, int column) {
        for(int i = row - 1; i < row + 1; i++) {
            //goes through the immediate antecedent and descendent rows

            for(int j = column; j < column + 1; j++) {
                //goes through current column plus one as these are the general cases
            int[] holder = {i, j};
            boolean instanceFound = false;

                for(int k = 0; k < placeableArea.size(); k++) {
                    if(placeableArea.get(k) == holder || (holder[0] == row && holder[1] == column)) {
                        instanceFound = true;
                        break;
                    }
                }
                /*breaks if the current position is already in placeableArea ArrayList or
                if the current holder is the current tiles being placed*/

                if(!instanceFound && board[i][j].isBlank()) {
                placeableArea.add(holder);
                }
            }
        }
        int[] temp = {row, column - 1};
        for(int k = 0; k < placeableArea.size(); k++) {
            if(placeableArea.get(k) == temp) {
                return;
            }
            else {
                placeableArea.add(temp);
            }
        }
        /*the edge case tile, the one on the same row but one column behind, outside of previous
        for loop as it is outside the general case*/
    }

    //Method to place a tile at a co-ordinate from a given index of the placeableArea array
    public void placeTile(Tile tile, int n) {
        int[] coOrd = placeableArea.get(n);
        addTile(tile, coOrd[0], coOrd[1]);
    }
}