import java.util.Random;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

public enum Wildlife {
    FOX("Fox",ViewColours.ORANGE + ViewColours.WHITE_BG + " F " + ViewColours.RESET),
    ELK("Elk",ViewColours.BLACK + ViewColours.WHITE_BG + " E " + ViewColours.RESET),
    BEAR("Bear",ViewColours.BROWN + ViewColours.WHITE_BG + " B " + ViewColours.RESET),
    HAWK("Hawk",ViewColours.CYAN + ViewColours.WHITE_BG + " H " + ViewColours.RESET),
    SALMON("Salmon",ViewColours.PINK + ViewColours.WHITE_BG + " S " + ViewColours.RESET),
    //enums to be used on tiles as placeholders for the wildlifes compatible there

    FOX_PLACED("Fox", ViewColours.WHITE + ViewColours.ORANGE_BG + " F " + ViewColours.RESET),
    ELK_PLACED("Elk", ViewColours.WHITE + ViewColours.BLACK_BG + " E " + ViewColours.RESET),
    BEAR_PLACED("Bear", ViewColours.WHITE + ViewColours.BROWN_BG + " B " + ViewColours.RESET),
    HAWK_PLACED("Hawk", ViewColours.WHITE + ViewColours.CYAN_BG + " H " + ViewColours.RESET),
    SALMON_PLACED("Salmon", ViewColours.WHITE + ViewColours.PINK_BG + " S " + ViewColours.RESET),
    //enums to be used on the table to show available tokens, and also to signify that a certain token has been placed
    //on a tile

    ERASER("Eraser", ViewColours.WHITE + ViewColours.WHITE_BG + " O " + ViewColours.RESET),
    //changes tile and text to solid white
    BLANK_I_NATOR("Blank", ViewColours.RESET + "   " + ViewColours.RESET);
    //makes tile blank

    private String name;
    private String animalColour;
    private String textColour;
    private String bgColour;

    Wildlife(String animalName, String animalColour) {
        this.name = animalName;
        this.animalColour = animalColour;
        String[] splitArr = animalColour.split("m");
        this.textColour = splitArr[0] + "m";
        this.bgColour = splitArr[1] + "m";
    }

    public String toString() {
        return this.animalColour;
    }
    //returns string to print out colour of the enum type string

    public String getTextColour() {
        return textColour;
    }

    public String getBgColour() {
        return bgColour;
    }
    public String getName() {
        return name;
    }


    public static Wildlife chooseWildlife() {
        Random rnd = new Random();
        int y = rnd.nextInt(5);

        return Wildlife.values()[y];
    }
    //function to choose random wildlife

}