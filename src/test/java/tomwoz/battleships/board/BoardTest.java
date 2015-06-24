package tomwoz.battleships.board;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tomwoz.battleships.exceptions.ActionOutOfBoundsException;
import tomwoz.battleships.exceptions.NoShipException;
import tomwoz.battleships.exceptions.ShipCollisionException;
import tomwoz.battleships.ships.Orientation;
import tomwoz.battleships.ships.Ship;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BoardTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGetShip() throws Exception {

        Map<Coords, Ship> ships = new HashMap<>();
        final Ship ship1 = new Ship(Orientation.NORTH, true);
        ships.put(new Coords(0, 0), ship1);
        final Ship ship2 = new Ship(Orientation.EAST);
        ships.put(new Coords(5, 5), ship2);

        final Board board = new Board(10, ships);

        assertEquals(ship1, board.getShip(new Coords(0, 0)));
        assertEquals(ship2, board.getShip(new Coords(5, 5)));
    }

    @Test
    public void testThrowsNoBoatException() throws ActionOutOfBoundsException, NoShipException {

        final Board board = new Board(10, Collections.emptyMap());

        expectedException.expect(NoShipException.class);

        board.getShip(new Coords(3, 3));
    }

    @Test
    public void testThrowsActionOutOfBoundsException() throws ActionOutOfBoundsException, NoShipException {
        final Board board = new Board(10, Collections.emptyMap());

        expectedException.expect(ActionOutOfBoundsException.class);

        board.getShip(new Coords(10, 10));
    }

    @Test
    public void testThrowsActionOutOfBoundsExceptionNegativeCase() throws ActionOutOfBoundsException, NoShipException {
        final Board board = new Board(10, Collections.emptyMap());

        expectedException.expect(ActionOutOfBoundsException.class);

        board.getShip(new Coords(-1, -1));
    }

    @Test
    public void testTranslateShipLocation() throws ActionOutOfBoundsException, NoShipException, ShipCollisionException {
        Map<Coords, Ship> ships = new HashMap<>();
        final Ship ship1 = new Ship(Orientation.NORTH, true);
        ships.put(new Coords(0, 0), ship1);
        final Ship ship2 = new Ship(Orientation.EAST);
        ships.put(new Coords(5, 5), ship2);

        final Board board = new Board(10, ships);

        board.translateShipLocation(new Coords(0, 0), new Coords(9, 9));

        assertEquals(ship1, board.getShip(new Coords(9, 9)));
        assertEquals(ship2, board.getShip(new Coords(5, 5)));

        expectedException.expect(NoShipException.class);

        board.getShip(new Coords(0, 0));
    }

    @Test
    public void testTargetLocationIsOutOfBounds() throws ActionOutOfBoundsException, NoShipException, ShipCollisionException {
        Map<Coords, Ship> ships = new HashMap<>();
        final Ship ship1 = new Ship(Orientation.NORTH, true);
        ships.put(new Coords(0, 0), ship1);

        final Board board = new Board(10, ships);

        expectedException.expect(ActionOutOfBoundsException.class);

        board.translateShipLocation(new Coords(0, 0), new Coords(10, 10));
    }

    @Test
    public void testTargetLocationIsOutOfBoundsNegativeCase() throws ActionOutOfBoundsException, NoShipException, ShipCollisionException {
        Map<Coords, Ship> ships = new HashMap<>();
        final Ship ship1 = new Ship(Orientation.NORTH, true);
        ships.put(new Coords(0, 0), ship1);

        final Board board = new Board(10, ships);

        expectedException.expect(ActionOutOfBoundsException.class);

        board.translateShipLocation(new Coords(0, 0), new Coords(-1, -1));
    }

    @Test
    public void testShipCollision() throws ActionOutOfBoundsException, NoShipException, ShipCollisionException {
        Map<Coords, Ship> ships = new HashMap<>();
        final Ship ship1 = new Ship(Orientation.NORTH, true);
        ships.put(new Coords(0, 0), ship1);
        final Ship ship2 = new Ship(Orientation.EAST);
        ships.put(new Coords(5, 5), ship2);

        final Board board = new Board(10, ships);

        expectedException.expect(ShipCollisionException.class);

        board.translateShipLocation(new Coords(0, 0), new Coords(5, 5));
    }
}