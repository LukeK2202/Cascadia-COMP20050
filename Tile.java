import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//Tile class to construct and use tiles for the game
public class Tile {

    //Enum used to determine what tile type is wanted
    public static enum tileTypes {
        KEYSTONE,
        RANDOM,
        BLANK
    }
    //Boolean to show if tile is a blank tile or not
    Boolean blank = false;
    //Array list to be populated with the habitats the tile has
    ArrayList<Habitat> habitats = new ArrayList<>();
    //Array list to be populated with the wildlife the tile has
    ArrayList<Wildlife> wildlife = new ArrayList<>();
    //Main tile array, a 4x4 grid of strings which become the tile
    private String[][] tile = new String[4][4];
    //Spacing used as a constant between tiles
    public static String space = "   ";
    //Blank space(white)
    public static String whiteSpace = ViewColours.WHITE_BG + space + ViewColours.RESET;

    //Main tile constructor
    Tile(tileTypes type) {
        //Keystone tile constructor
        if(type == tileTypes.KEYSTONE) {
            //Populates the habitats and wildlife arraylists
            habitats = new ArrayList<Habitat>();
            habitats.add(Habitat.chooseHabitat());
            wildlife = new ArrayList<Wildlife>();
            wildlife.add(Wildlife.chooseWildlife());

            //Double for loop to iterate through each entry in the 4x4 array
            for(int i = 0; i < tile.length; i++) {
                for (int j = 0; j < tile.length; j++) {
                    //If statement to check if the current location is an outer edge, meaning it is a habitat slot
                    if (i == 0 || i == tile.length - 1 || j == 0 || j == tile[i].length - 1) {
                        //Fill with the habitat
                        this.tile[i][j] = habitats.get(0) + space + ViewColours.RESET;
                        //Else it is in the center of the tile
                    } else {
                        //If to check that the index is in the first position for wildlife
                        if (i == 1 && j == 1 && wildlife.size() == 1) {
                            this.tile[i][j] = wildlife.get(0).toString();
                        } else {
                            //Else print blank white space
                            this.tile[i][j] = whiteSpace;
                        }
                    }
                }
            }
        }

        //Constructor for a random tile
        else if(type == tileTypes.RANDOM) {
            //Block to make a habitat arraylist and fill it with 2 different habitats
            habitats = new ArrayList<Habitat>();
            Habitat hab1 = Habitat.chooseHabitat();
            Habitat hab2 = Habitat.chooseHabitat();
            while(hab1 == hab2) {
                hab2 = Habitat.chooseHabitat();
            }
            habitats.add(hab1);
            habitats.add(hab2);
            //Block to make a wildlife arraylist and fill it with randomly selected 2 or 3 different wildlife
            wildlife = new ArrayList<Wildlife>();
            Random rnd = new Random();
            Wildlife wild1 = Wildlife.chooseWildlife();
            Wildlife wild2 = Wildlife.chooseWildlife();
            while(wild1 == wild2) {
                wild2 = Wildlife.chooseWildlife();
            }
            wildlife.add(wild1);
            wildlife.add(wild2);
            //Random if and while loop for if a 3rd wildlife is selected
            if(rnd.nextInt(3) == 2) {
                Wildlife wild3 = Wildlife.chooseWildlife();
                while(wild2 == wild3  || wild1 == wild3) {
                    wild3 = Wildlife.chooseWildlife();
                }
                wildlife.add(wild3);
            }

            //Double for loop to iterate through each location in the tile array
            for(int i = 0; i < tile.length; i++) {
                for(int j = 0; j < tile.length; j++) {
                    //If to check if its an outer edge of the matrix
                    if(i == 0 || i == tile.length - 1 || j == 0 || j == tile[i].length - 1) {
                        //Secondary if to split between the first habitat locations and the second
                        if(j < 2) {
                            this.tile[i][j] = habitats.get(0) + space + ViewColours.RESET;
                        } else {
                            this.tile[i][j] = habitats.get(1) + space + ViewColours.RESET;
                        }
                        //Else it is in the center of the tile
                    } else {
                        //Multiple if's to check the wildlife locations and if there are 2 or 3 wildlife
                        if (i == 1 && j == 1 && wildlife.size() >= 1) {
                            this.tile[i][j] = wildlife.get(0).toString();
                        } else if (i == 1 && j == 2 && wildlife.size() >= 2) {
                            this.tile[i][j] = wildlife.get(1).toString();
                        } else if (i == 2 && j == 1 && wildlife.size() == 3) {
                            this.tile[i][j] = wildlife.get(2).toString();
                        } else {
                            this.tile[i][j] = whiteSpace;
                        }
                    }
                }
            }

        }


        //Else its an empty tile
       else {
           for(int i = 0; i < tile.length; i++) {
               //Fill tile with space
               Arrays.fill(tile[i], space);
           }
           this.blank = true;
       }
    }

    //Rotate function to rotate a tile by 2 slots
    public void rotate() {
        String tmp = tile[1][0];
        String tmp2 = tile[2][0];
        tile[1][0] = tile[3][0];
        tile[2][0] = tile[3][1];
        tile[3][0] = tile[3][2];
        tile[3][1] = tile[3][3];
        tile[3][2] = tile[2][3];
        tile[3][3] = tile[1][3];
        tile[2][3] = tile[0][3];
        tile[1][3] = tile[0][2];
        tile[0][3] = tile[0][1];
        tile[0][2] = tile[0][0];
        tile[0][1] = tmp;
        tile[0][0] = tmp2;
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

    //Draw method to draw a whole tile in its own row
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

    //To string method used to draw tiles in-line
    public String toString(int row) {
        String out ="";
        for(int j = 0; j < tile[row].length; j++) {
            out += tile[row][j];
        }
        return out;
    }

    //Method to check if the tile is blank
    public Boolean isBlank() {
        return blank;
    }

    //Method to return the wildlife arraylist
    public ArrayList<Wildlife> getAnimals() {
        return wildlife;
    }

    //Method to return the habitat arraylist
    public ArrayList<Habitat> getHabitats() {
        return habitats;
    }
}
