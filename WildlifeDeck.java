import java.util.*;

public class WildlifeDeck extends Stack<Wildlife>{


    WildlifeDeck() {
        super();

            for(int i = 0; i < 20; i++) {

                super.add(Wildlife.BEAR_PLACED);
                super.add(Wildlife.HAWK_PLACED);
                super.add(Wildlife.ELK_PLACED);
                super.add(Wildlife.FOX_PLACED);
                super.add(Wildlife.SALMON_PLACED);
            }
    }
}
