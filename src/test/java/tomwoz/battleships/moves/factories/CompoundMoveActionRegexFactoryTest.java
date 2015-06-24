package tomwoz.battleships.moves.factories;

import org.junit.Test;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.CompoundMoveAction;

import static org.junit.Assert.*;

public class CompoundMoveActionRegexFactoryTest {

    @Test
    public void testCanBuild() throws Exception {
        final CompoundMoveActionRegexFactory factory = new CompoundMoveActionRegexFactory();

        //Valid cases
        assertTrue(factory.canBuild("(0, 10) MRLRLRM"));
        assertTrue(factory.canBuild("(100000000, 29999999) M"));

        //Invalid cases
        assertFalse(factory.canBuild("(9, 2)"));
        assertFalse(factory.canBuild(""));
        assertFalse(factory.canBuild("(9, 2)(9, 2)"));
        assertFalse(factory.canBuild("(0, 10) MRLRLRMT"));

    }

    @Test
    public void testFromString() throws Exception {

        final CompoundMoveActionRegexFactory factory = new CompoundMoveActionRegexFactory();

        final Coords coords = new Coords(0, 1);
        final CompoundMoveAction generatedMove1 = new CompoundMoveAction(coords, "MRLMRLM");

        assertEquals(generatedMove1, factory.fromString("(0, 1) MRLMRLM"));

        final Coords coords2 = new Coords(10000, 3000);
        final CompoundMoveAction generatedMove2 = new CompoundMoveAction(coords2, "MMMMMMMMMMM");

        assertEquals(generatedMove2, factory.fromString("(10000, 3000) MMMMMMMMMMM"));

        assertNull(factory.fromString("sfdemesodfmawld"));
    }
}