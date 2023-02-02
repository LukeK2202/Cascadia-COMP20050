import java.util.ArrayList;
import java.util.Random;

public enum Wildlife {
    FOX(ViewColours.ORANGE + ViewColours.WHITE_BG + " F "+ ViewColours.RESET),
    ELK(ViewColours.BLACK + ViewColours.WHITE_BG + " E "+ ViewColours.RESET),
    BEAR(ViewColours.BROWN + ViewColours.WHITE_BG + " B "+ ViewColours.RESET),
    HAWK(ViewColours.BLUE + ViewColours.WHITE_BG + " H "+ ViewColours.RESET),
    SALMON(ViewColours.PINK + ViewColours.WHITE_BG + " S "+ ViewColours.RESET);

    private String animalColour;

    Wildlife(String animalColour) {
        this.animalColour = animalColour;
    }

    public String toString() {
        return this.animalColour;
    }


    public static Wildlife chooseWildlife() {
        Random rnd = new Random();
        int y = rnd.nextInt(5);

        return Wildlife.values()[y];
    }

    public static ArrayList<Wildlife> chooseWildlifeArr() {
        Random rnd = new Random();
        ArrayList<Wildlife> animals = new ArrayList<Wildlife>();
        int num = rnd.nextInt(3);
        for(int i = 0; i <= num; i++){
            Wildlife animal = chooseWildlife();
            if(!animals.contains(animal)) {
                animals.add(animal);
            }
        }
        return animals;
    }
}