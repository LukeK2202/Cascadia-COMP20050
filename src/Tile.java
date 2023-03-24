import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Exceptions.*;

//Tile class to construct and use tiles for the game
public class Tile {

    //Enum used to determine what tile type is wanted
    public static enum tileTypes {
        KEYSTONE,
        RANDOM,
        BLANK
    }
    private Boolean blank = false;
    private ArrayList<Habitat> habitats = new ArrayList<>();
    private ArrayList<Wildlife> wildlife = new ArrayList<>();
    //Main tile array, a 4x4 grid of strings which become the tile
    private String[][] tile = new String[4][4];
    //Spacing used as a constant between tiles
    public static String space = "   ";
    //Blank space(white)
    public static String whiteSpace = ViewColours.WHITE_BG + space + ViewColours.RESET;
    //The current Co-Ord index of the tile
    private int coOrd = 0;
    private Wildlife placedToken;

    private boolean keystoneTile = false;

    public Wildlife getPlacedToken() {
        return placedToken;
    }

    Tile(tileTypes type) {
        //Keystone tile constructor
        if(type == tileTypes.KEYSTONE) {
            //Populates the habitats and wildlife arraylists
            habitats = new ArrayList<Habitat>();
            habitats.add(Habitat.chooseHabitat());
            wildlife = new ArrayList<Wildlife>();
            wildlife.add(Wildlife.chooseWildlife());
            keystoneTile = true;

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


        //Else it's an empty tile
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

    //Displays the held coOrd index of the tile
    public void showCoOrd() {
        if(isBlank()) {
            if(coOrd < 10) {
                this.tile[2][2] = " " + coOrd + " ";
            } else {
                this.tile[2][2] = " " + coOrd + "";
            }
        } else if(hasPlacedToken()) {
            if(coOrd < 10){
                this.tile[2][2] = placedToken.getBgColour() + " " + coOrd + " " + ViewColours.RESET;
            } else {
                this.tile[2][2] = placedToken.getBgColour() + " " + coOrd + "" + ViewColours.RESET;
            }
        } else {
            if(coOrd < 10){
                this.tile[2][2] = ViewColours.WHITE_BG + " " + coOrd + " " + ViewColours.RESET;
            } else {
                this.tile[2][2] = ViewColours.WHITE_BG + " " + coOrd + "" + ViewColours.RESET;
            }
        }
        
    }

    //Hides the coOrd index of the tile
    public void hideCoOrd() {
        if(isBlank()) {
            this.tile[2][2] = space;
        } else if(hasPlacedToken()) {
            this.tile[2][2] = placedToken.getBgColour() + space + ViewColours.RESET;
        } else {
            this.tile[2][2] = whiteSpace;
        }
    }

    //Sets the coOrd index of the tile, meant to take in the index of the coOrd from board class
    public void setCoOrd(int coOrd) {
        this.coOrd = coOrd;
    }

    //Returds the coOrd index of the tile
    public int getCoOrd() {
        return coOrd;
    }

    public boolean hasPlacedToken() {
        return placedToken != null;
    }

    public boolean isKeystoneTile() {
        return keystoneTile;
    }

    public void addWildlifeToken(Wildlife wildlifeToken) throws CantPlaceWildlifeException {
        int index = -1;
        String[] tokenToBePlaced = wildlifeToken.name().split("_");

        if (hasPlacedToken()) {
            throw new CantPlaceWildlifeException("Wildlife Token already placed");
        }

        for(int j = 0; j < wildlife.size(); j++) {
            if(wildlife.get(j).name().equals(tokenToBePlaced[0])) {
                index = j;
                break;
            }
        }
        if(index == -1) {
            throw new CantPlaceWildlifeException("Tile does not contain a matching wildlife to place. Please choose another tile, or discard token.");
        }
        else {
            placedToken = wildlifeToken;

            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    if(i == 0 && j == 0) {
                        tile[i+1][j+1] = wildlifeToken.toString();
                    } else {
                        tile[i+1][j+1] = wildlifeToken.getBgColour() + space + ViewColours.RESET;
                    }
                }
            }
        }
    }
    
}
