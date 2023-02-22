import java.util.ArrayList;

//Main board class that will contain the tiles for each user
public class Board {

    //Main board matrix, a grid of tile classes
    Tile[][] board = new Tile[11][11];
    ArrayList<int[]> occPlaces = new ArrayList<int[]>();
    //Board constructor, adds 3 tiles as a started tile. 1 keystone and 2 randoms
    //Origin of board is 6,6.
    Board() {

        addTile(new Tile(Tile.tileTypes.KEYSTONE), 6, 6);
        addTile(new Tile(Tile.tileTypes.RANDOM), 7, 5);
        addTile(new Tile(Tile.tileTypes.RANDOM), 7, 6);
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

    //Method to add a tile to the board, requiring the row and column
    public void addTile(Tile tile ,int row, int column) {
        if(isRowNull(row)) {
            populateRow(row);
        }
        board[row][column] = tile;
    }
}