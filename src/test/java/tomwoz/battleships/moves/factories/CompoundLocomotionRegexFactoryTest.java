package tomwoz.battleships.moves.factories;

import org.junit.Test;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.CompoundLocomotionMove;

import static org.junit.Assert.*;

public class CompoundLocomotionRegexFactoryTest {

    @Test
    public void testCanBuild() throws Exception {
        final CompoundLocomotionRegexFactory factory = new CompoundLocomotionRegexFactory();

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

        final CompoundLocomotionRegexFactory factory = new CompoundLocomotionRegexFactory();

        final Coords coords = new Coords(0, 1);
        final CompoundLocomotionMove generatedMove1 = new CompoundLocomotionMove(coords, "MRLMRLM");

        assertEquals(generatedMove1, factory.fromString("(0, 1) MRLMRLM"));

        final Coords coords2 = new Coords(10000, 3000);
        final CompoundLocomotionMove generatedMove2 = new CompoundLocomotionMove(coords2, "MMMMMMMMMMM");

        assertEquals(generatedMove2, factory.fromString("(10000, 3000) MMMMMMMMMMM"));

        assertNull(factory.fromString("sfdemesodfmawld"));
    }
}