package tomwoz.battleships.moves.factories;

import org.junit.Test;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.SinkShipAction;

import static org.junit.Assert.*;

public class SinkShipActionRegexFactoryTest {

    @Test
    public void testCanBuild() throws Exception {
        final SinkShipActionRegexFactory factory = new SinkShipActionRegexFactory();

        //Valid cases
        assertTrue(factory.canBuild("(0, 10)    "));
        assertTrue(factory.canBuild("(100000000, 29999999)    "));

        //Invalid cases
        assertFalse(factory.canBuild("     (9, 2)"));
        assertFalse(factory.canBuild(""));
        assertFalse(factory.canBuild("(9, 2)(9, 2)"));
        assertFalse(factory.canBuild("(0, 10, 01) "));
    }

    @Test
    public void testFromString() throws Exception {
        final SinkShipActionRegexFactory factory = new SinkShipActionRegexFactory();

        assertEquals(new SinkShipAction(new Coords(0, 0)), factory.fromString("(0, 0)  "));
        assertEquals(new SinkShipAction(new Coords(100000, 100000)), factory.fromString("(100000, 100000)"));
        assertEquals(new SinkShipAction(new Coords(934, 0)), factory.fromString("(934, 0)  "));
        assertNull(factory.fromString("dwadwakdakwnd"));
    }
}