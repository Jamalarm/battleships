package tomwoz.battleships.ships;

import org.junit.Test;
import tomwoz.battleships.board.Coords;

import static org.junit.Assert.*;
import static tomwoz.battleships.ships.Direction.*;

public class DirectionTest {

    @Test
    public void testFromShortCode() throws Exception {
        assertEquals(fromShortCode('N'), NORTH);
        assertEquals(fromShortCode('S'), SOUTH);
        assertEquals(fromShortCode('E'), EAST);
        assertEquals(fromShortCode('W'), WEST);
    }

    @Test
    public void testMoveForward() throws Exception {
        final Coords base = new Coords(0, 0);

        final Coords northMove = NORTH.moveForward(NORTH.moveForward(NORTH.moveForward(base)));
        assertEquals(new Coords(0, 3), northMove);

        final Coords southMove = SOUTH.moveForward(SOUTH.moveForward(SOUTH.moveForward(base)));
        assertEquals(new Coords(0, -3), southMove);

        final Coords eastMove = EAST.moveForward(EAST.moveForward(EAST.moveForward(base)));
        assertEquals(new Coords(3, 0), eastMove);

        final Coords westMove = WEST.moveForward(WEST.moveForward(WEST.moveForward(base)));
        assertEquals(new Coords(-3, 0), westMove);
    }

    @Test
    public void testTurnRight() throws Exception {
        assertEquals(NORTH.turnRight(), EAST);
        assertEquals(EAST.turnRight(), SOUTH);
        assertEquals(SOUTH.turnRight(), WEST);
        assertEquals(WEST.turnRight(), NORTH);
    }

    @Test
    public void testTurnLeft() throws Exception {
        assertEquals(NORTH.turnLeft(), WEST);
        assertEquals(WEST.turnLeft(), SOUTH);
        assertEquals(SOUTH.turnLeft(), EAST);
        assertEquals(EAST.turnLeft(), NORTH);
    }
}