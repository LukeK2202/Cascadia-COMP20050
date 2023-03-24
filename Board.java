import Exceptions.CantPlaceTileException;
import Exceptions.CantPlaceWildlifeException;

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

    public int[] getTilePlacedCoOrd(int n) {
        return occupiedTiles.get(n);
    }

    public int[] getTileAvailableCoOrd(int n) {
        return placeableArea.get(n);
    }

    public ArrayList<int[]> getOccupiedTileArray() {
        return occupiedTiles;
    }

    //Method to add a tile to the board, requiring the row and column
    private void addTile(Tile tile ,int row, int column) {
        if(isRowNull(row)) {
            populateRow(row);
        }
        if(row > 1) {
            if(isRowNull(row - 1)) {
                populateRow(row - 1);
            }
        }
        if(row + 1 < getBoardLength()) {
            if(isRowNull(row + 1)) {
                populateRow(row + 1);
            }
        }
        int coOrd[] = {row, column};
        occupiedTiles.add(coOrd);
        board[row][column] = tile;
        tile.setCoOrd(occupiedTiles.indexOf(coOrd));
    }
    //Method to place a tile at a co-ordinate from a given index of the placeableArea array
    public void placeTile(Tile tile, int n) throws CantPlaceTileException{
        if(tile == null) {
            throw new CantPlaceTileException("No tile selected. Please select a tile first.");
        }
        if(n >= placeableArea.size()) {
            throw new CantPlaceTileException("Entered index beyond tracked index's. Try Again.");
        }
        int[] coOrd = placeableArea.get(n);
        addTile(tile, coOrd[0], coOrd[1]);
        placeableArea.remove(n);
    }


    public void placeWildlife(int index, Wildlife wildlifeToken) throws CantPlaceWildlifeException {
        if(wildlifeToken == null) {
            throw new CantPlaceWildlifeException("No wildlife selected. Please select a wildlife first.");
        }
        if(index >= occupiedTiles.size()) {
            throw new CantPlaceWildlifeException("Entered index beyond tracked index's. Try Again.");
        }
        int[] tempCoOrdHolder = occupiedTiles.get(index);
        board[tempCoOrdHolder[0]][tempCoOrdHolder[1]].addWildlifeToken(wildlifeToken);
    }

    public void displayAvailableAreas() {
        for(int i = 0; i < placeableArea.size(); i++) {
            int[] coOrd = placeableArea.get(i);
            board[coOrd[0]][coOrd[1]].setCoOrd(i);
            board[coOrd[0]][coOrd[1]].showCoOrd();
        }
    }

    public void hideAvailableAreas() {
        for(int i = 0; i < placeableArea.size(); i++) {
            int[] coOrd = placeableArea.get(i);
            board[coOrd[0]][coOrd[1]].setCoOrd(i);
            board[coOrd[0]][coOrd[1]].hideCoOrd();
        }

    }public void displayPlacedAreas() {
        for(int i = 0; i < occupiedTiles.size(); i++) {
            int[] coOrd = occupiedTiles.get(i);
            board[coOrd[0]][coOrd[1]].setCoOrd(i);
            board[coOrd[0]][coOrd[1]].showCoOrd();
        }
    }

    public void hidePlacedAreas() {
        for(int i = 0; i < occupiedTiles.size(); i++) {
            int[] coOrd = occupiedTiles.get(i);
            board[coOrd[0]][coOrd[1]].setCoOrd(i);
            board[coOrd[0]][coOrd[1]].hideCoOrd();
        }
    }
    public boolean isCoOrdsContained(ArrayList<int[]> list, int[] coOrd) {
        for(int[] listCoOrd : list) {
            if(listCoOrd[0] == coOrd[0] && listCoOrd[1] == coOrd[1]) {
                return true;
            }
        }
        return false;
    }

    public void checkPLaceableArea() {
        for(int[] coOrd : occupiedTiles) {
            int row = coOrd[0];
            int column = coOrd[1];
            int[] newCoOrd;
            if(row > 1) {
                if(row % 2 == 1) {
                    newCoOrd = new int[]{(row - 1), column};
                    if(board[row -1][column].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                        placeableArea.add(newCoOrd);
                    } if(column < getBoardWidth()) {
                        newCoOrd = new int[]{(row - 1), column + 1};
                        if(board[row -1][column + 1].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                            placeableArea.add(newCoOrd);
                        }
                    }
                } else {
                    newCoOrd = new int[]{(row - 1), column};
                    if(board[row -1][column].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                        placeableArea.add(newCoOrd);
                    } if(column > 0) {
                        newCoOrd = new int[]{(row - 1), column - 1};
                        if(board[row -1][column - 1].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                            placeableArea.add(newCoOrd);
                        }
                    }
                }
            }

            if(column > 0) {
                newCoOrd = new int[]{(row), column - 1};
                if(board[row][column - 1].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                    placeableArea.add(newCoOrd);
                }
            }if(column + 1 < getBoardWidth()) {
                newCoOrd = new int[]{(row), column + 1};
                if(board[row][column + 1].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                    placeableArea.add(newCoOrd);
                }
            }

            if(row + 1 < getBoardLength()) {
                if(row % 2 == 1) {
                    newCoOrd = new int[]{(row + 1), column};
                    if(board[row + 1][column].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                        placeableArea.add(newCoOrd);
                    } if(column < getBoardWidth()) {
                        newCoOrd = new int[]{(row + 1), column + 1};
                        if(board[row + 1][column + 1].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                            placeableArea.add(newCoOrd);
                        }
                    }
                } else {
                    newCoOrd = new int[]{(row + 1), column };
                    if(board[row + 1][column].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                        placeableArea.add(newCoOrd);
                    } if(column > 0) {
                        newCoOrd = new int[]{(row + 1), column - 1};
                        if(board[row + 1][column - 1].isBlank() && !isCoOrdsContained(placeableArea, newCoOrd)) {
                            placeableArea.add(newCoOrd);
                        }
                    }
                }
            }

        }
    }
}