import java.util.Random;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

public enum Habitat {
    WOODLAND(ViewColours.D_GREEN_BG),
    RIVER(ViewColours.BLUE_BG),
    PRAIRIE(ViewColours.YELLOW_BG),
    MOUNTAINS(ViewColours.GREY_BG),
    WETLAND(ViewColours.GREEN_BG);
    // enum types with string values that will output a background colour when prompted to print

    private String placeColour;

    Habitat(String placeColour) {
        this.placeColour = placeColour;
    }
    //uses local string variable to store the string colour values from the enums

    public String toString() {
        return this.placeColour;
    }
    //returns colour string
    public static Habitat chooseHabitat() {
        Random rnd = new Random();
        int x = rnd.nextInt(5);

        return Habitat.values()[x];
    }
    //function to choose a habitat
    
}