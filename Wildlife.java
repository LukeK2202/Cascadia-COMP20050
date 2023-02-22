import java.util.ArrayList;
import java.util.Random;

public enum Wildlife {
    FOX(ViewColours.ORANGE + ViewColours.WHITE_BG + " F "+ ViewColours.RESET),
    ELK(ViewColours.BLACK + ViewColours.WHITE_BG + " E "+ ViewColours.RESET),
    BEAR(ViewColours.BROWN + ViewColours.WHITE_BG + " B "+ ViewColours.RESET),
    HAWK(ViewColours.BLUE + ViewColours.WHITE_BG + " H "+ ViewColours.RESET),
    SALMON(ViewColours.PINK + ViewColours.WHITE_BG + " S "+ ViewColours.RESET),
    //enums to be used on tiles as placeholders for the wildlifes compatible there

    FOX_PLACED(ViewColours.WHITE + ViewColours.ORANGE_BG + " F " + ViewColours.RESET),
    ELK_PLACED(ViewColours.WHITE + ViewColours.BLACK_BG + " E " + ViewColours.RESET),
    BEAR_PLACED(ViewColours.WHITE + ViewColours.BROWN_BG + " B " + ViewColours.RESET),
    HAWK_PLACED(ViewColours.WHITE + ViewColours.BLUE_BG + " H " + ViewColours.RESET),
    SALMON_PLACED(ViewColours.WHITE + ViewColours.PINK_BG + " S " + ViewColours.RESET);
    //enums to be used on the table to show available tokens, and also to signify that a certain token has been placed
    //on a tile

    private String animalColour;

    Wildlife(String animalColour) {
        this.animalColour = animalColour;
    }

    public String toString() {
        return this.animalColour;
    }
    //returns string to print out colour of the enum type string


    public static Wildlife chooseWildlife() {
        Random rnd = new Random();
        int y = rnd.nextInt(5);

        return Wildlife.values()[y];
    }
    //function to choose random wildlife

}