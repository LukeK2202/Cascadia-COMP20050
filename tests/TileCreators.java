import Exceptions.*;

import static org.junit.jupiter.api.Assertions.fail;

/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

public class TileCreators {

    public Tile createBearTile() {
        Tile t1;
        do {
            t1 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t1.getAnimals().contains(Wildlife.BEAR));
        return t1;
    }

    public Tile createPlacedBearTile() {
        Tile t1 = createBearTile();
        try {
            t1.addWildlifeToken(Wildlife.BEAR_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldn't Place bear token");
        }
        return t1;
    }

    public Tile createHawkTile() {
        Tile t1;
        do {
            t1 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t1.getAnimals().contains(Wildlife.HAWK));
        return t1;
    }

    public Tile createPlacedHawkTile() {
        Tile t1 = createHawkTile();
        try {
            t1.addWildlifeToken(Wildlife.HAWK_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldn't Place Hawk token");
        }
        return t1;
    }

    public Tile createElkTile() {
        Tile t1;
        do {
            t1 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t1.getAnimals().contains(Wildlife.ELK));
        return t1;
    }

    public Tile createPlacedElkTile() {
        Tile t1 = createElkTile();
        try {
            t1.addWildlifeToken(Wildlife.ELK_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldn't Place Elk token");
        }
        return t1;
    }

    public Tile createFoxTile() {
        Tile t1;
        do {
            t1 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t1.getAnimals().contains(Wildlife.FOX));
        return t1;
    }

    public Tile createPlacedFoxTile() {
        Tile t1 = createFoxTile();
        try {
            t1.addWildlifeToken(Wildlife.FOX_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldn't Place fox token");
        }
        return t1;
    }

    public Tile createSalmonTile() {
        Tile t1;
        do {
            t1 = new Tile(Tile.tileTypes.KEYSTONE);
        } while(!t1.getAnimals().contains(Wildlife.SALMON));
        return t1;
    }

    public Tile createPlacedSalmonTile() {
        Tile t1 = createSalmonTile();
        try {
            t1.addWildlifeToken(Wildlife.SALMON_PLACED);
        } catch (CantPlaceWildlifeException ex) {
            fail("Couldn't Place Salmon token");
        }
        return t1;
    }
}
