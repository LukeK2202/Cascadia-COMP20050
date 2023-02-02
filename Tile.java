import java.util.ArrayList;

public class Tile {


    ArrayList<Habitat> habitats = new ArrayList<>();
    ArrayList<Wildlife> wildlife = new ArrayList<>();
    private String[][] tile = new String[4][4];;
    public static String space = "   ";
    public static String blankSpace = ViewColours.WHITE_BG + space + ViewColours.RESET;
    Tile() {
        for(int i = 0; i < tile.length; i++) {
            for(int j = 0; j < tile[i].length; j++) {
                tile[i][j] = space;
            }
        }
    }

    Tile(ArrayList<Habitat> habitat, ArrayList<Wildlife> animal) {
        habitats = habitat;
        wildlife = animal;

        for(int i = 0; i < tile.length; i++) {
            for(int j = 0; j < tile.length; j++) {
                if(i == 0 || i == tile.length - 1 || j == 0 || j == tile[i].length - 1) {
                    if((i == 0 || (i < tile.length - 1 && j == 0)) && habitat.size() > 1) {
                        this.tile[i][j] = habitat.get(1) + space + ViewColours.RESET;
                    } else {
                        this.tile[i][j] = habitat.get(0) + space + ViewColours.RESET;
                    }
                } else {
                    if(animal.size() > 0) {
                        this.tile[i][j] = animal.get(0).toString();
                        animal.remove(0);
                    } else {
                        this.tile[i][j] = blankSpace;
                    }
                }
            }
        }
    }

    Tile(Habitat habitat, Habitat habitat2, ArrayList<Wildlife> animal) {
        habitats.add(habitat); habitats.add(habitat2);
        wildlife = animal;

        for(int i = 0; i < tile.length; i++) {
            for(int j = 0; j < tile.length; j++) {
                if(i == 0 || i == tile.length - 1 || j == 0 || j == tile[i].length - 1) {
                    if(i == 0 || (i < tile.length - 1 && j == 0)) {
                        this.tile[i][j] = habitat + space + ViewColours.RESET;
                    } else {
                        this.tile[i][j] = habitat2 + space + ViewColours.RESET;
                    }

                } else {
                    if(animal.size() > 0) {
                        this.tile[i][j] = animal.get(0).toString();
                        animal.remove(0);
                    } else {
                        this.tile[i][j] = blankSpace;
                    }
                }
            }
        }
    }

    public void draw() {
        String out = "";
        for(int i = 0; i < tile.length; i++) {
            for(int j = 0; j < tile[i].length; j++) {
                out += tile[i][j];
            }
            System.out.println(out);
            out = "";
        }
    }

    public String returnRow(int row) {
        String out ="";
        for(int j = 0; j < tile[row].length; j++) {
            out += tile[row][j];
        }
        return out;
    }

}
