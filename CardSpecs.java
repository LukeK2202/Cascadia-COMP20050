import java.util.Random;

enum habitat {
    WOODLAND,
    WATERAREA,
    SANDY,
    MOUNTAINS
}

enum wildlife {
    FOX,
    ELK,
    BEAR,
    HAWK,
    SALMON
}

public class CardSpecs {

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