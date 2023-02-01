import java.util.Random;

public class Deck {


    Random place = new Random();

    public String chooseHabitat() {

        int x = place.nextInt(4);

        return String.valueOf(habitat.values()[x]);
    }

    public String chooseWildlife() {

        int y = place.nextInt(5);

        return String.valueOf(wildlife.values()[y]);
    }
}
}
