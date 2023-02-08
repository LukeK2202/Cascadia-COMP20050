public class Board {

    Tile[][] board = new Tile[11][11];
    Board() {
        addTile(new Tile(Habitat.chooseHabitatArr(), Wildlife.chooseWildlifeArr()), 6, 6);
        addTile(new Tile(Habitat.chooseHabitatArr(), Wildlife.chooseWildlifeArr()), 7, 5);
        addTile(new Tile(Habitat.chooseHabitatArr(), Wildlife.chooseWildlifeArr()), 7, 6);
    }

    public int getBoardLength() {
        return board.length;
    }

    public int getBoardWidth() {
        return board[0].length;
    }

    public boolean isRowNull(int row) {
        for(int i = 0; i < board[row].length; i++) {
            if(board[row][i] == null) {
                return true;
            }
        }
        return false;
    }

    private void populateRow(int row) {
        for(int i = 0; i < board[row].length; i++) {
            board[row][i] = new Tile();
        }
    }

    public void addTile(Tile tile ,int row, int column) {
        if(isRowNull(row)) {
            populateRow(row);
        }
        board[row][column] = tile;
    }
}