import java.util.ArrayList;
import java.util.Random;

public enum Habitat {
    WOODLAND(ViewColours.D_GREEN_BG),
    RIVER(ViewColours.BLUE_BG),
    PRAIRIE(ViewColours.YELLOW_BG),
    MOUNTAINS(ViewColours.GREY_BG),
    WETLAND(ViewColours.GREEN_BG);

    private String placeColour;

    Habitat(String placeColour) {
        this.placeColour = placeColour;
    }

    public String toString() {
        return this.placeColour;
    }
    public static Habitat chooseHabitat() {
        Random rnd = new Random();
        int x = rnd.nextInt(5);

        return Habitat.values()[x];
    }

    public static ArrayList<Habitat> chooseHabitatArr() {
        Random rnd = new Random();
        ArrayList<Habitat> habitats  = new ArrayList<>();
        int num = rnd.nextInt(3);
        for(int i = 0; i <= num; i++){
            Habitat habitat = chooseHabitat();
            if(!habitats.contains(habitat)) {
                habitats.add(habitat);
            }
        }
        return habitats;
    }

}