import java.util.Random;

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