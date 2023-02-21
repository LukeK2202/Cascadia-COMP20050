import java.util.*;

public class WildlifeDeck extends Stack<Wildlife>{


    WildlifeDeck() {
        super();
        // adds twenty of each of the wildlife tokens to a stack type database called WildlifeDeck

            for(int i = 0; i < 20; i++) {

                super.add(Wildlife.BEAR_PLACED);
                super.add(Wildlife.HAWK_PLACED);
                super.add(Wildlife.ELK_PLACED);
                super.add(Wildlife.FOX_PLACED);
                super.add(Wildlife.SALMON_PLACED);
            }
    }
}
