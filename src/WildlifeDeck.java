import java.util.*;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

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
