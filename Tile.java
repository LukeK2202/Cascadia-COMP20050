import java.util.ArrayList;

public class Tile {


    Boolean blank = false;
    ArrayList<Habitat> habitats = new ArrayList<>();
    ArrayList<Wildlife> wildlife = new ArrayList<>();
    private String[][] tile = new String[4][4];;
    public static String space = "   ";
    public static String blankSpace = ViewColours.WHITE_BG + space + ViewColours.RESET;

    String[] top = {tile[0][0], tile[0][1], tile[0][2], tile[0][3]};
    Tile() {
        for(int i = 0; i < tile.length; i++) {
            for(int j = 0; j < tile[i].length; j++) {
                tile[i][j] = space;
            }
        }
        this.blank = true;
    }

    Tile(ArrayList<Habitat> habitat, ArrayList<Wildlife> animal) {
        habitats = new ArrayList<>(habitat);
        wildlife = new ArrayList<>(animal);

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

//    Tile(Habitat habitat, Habitat habitat2, ArrayList<Wildlife> animal) {
//        habitats.add(habitat); habitats.add(habitat2);
//        wildlife = animal;
//
//        for(int i = 0; i < tile.length; i++) {
//            for(int j = 0; j < tile.length; j++) {
//                if(i == 0 || i == tile.length - 1 || j == 0 || j == tile[i].length - 1) {
//                    if(i == 0 || (i < tile.length - 1 && j == 0)) {
//                        this.tile[i][j] = habitat + space + ViewColours.RESET;
//                    } else {
//                        this.tile[i][j] = habitat2 + space + ViewColours.RESET;
//                    }
//
//                } else {
//                    if(animal.size() > 0) {
//                        this.tile[i][j] = animal.get(0).toString();
//                        animal.remove(0);
//                    } else {
//                        this.tile[i][j] = blankSpace;
//                    }
//                }
//            }
//        }
//    }

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

    public String toString(int row) {
        String out ="";
        for(int j = 0; j < tile[row].length; j++) {
            out += tile[row][j];
        }
        return out;
    }

    public Boolean isBlank() {
        return blank;
    }

    public ArrayList<Wildlife> getAnimals() {
        return wildlife;
    }
    public ArrayList<Habitat> getHabitats() {
        return habitats;
    }
}
