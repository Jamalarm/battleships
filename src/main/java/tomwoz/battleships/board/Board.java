package tomwoz.battleships.board;

import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.exceptions.ActionOutOfBoundsException;
import tomwoz.battleships.exceptions.NoShipException;
import tomwoz.battleships.exceptions.ShipCollisionException;
import tomwoz.battleships.ships.Ship;

import java.util.Arrays;
import java.util.Map;

public class Board implements IBoard {

    private final Ship[][] shipGrid;

    public Board(int size, Map<Coords, Ship> ships) {
        shipGrid = new Ship[size][];
        for (int i = 0; i < size; i++) {
            shipGrid[i] = new Ship[size];
        }

        for (Map.Entry<Coords, Ship> ship : ships.entrySet()) {
            final Coords coords = ship.getKey();
            final int x = coords.getX();
            final int y = coords.getY();
            if (isWithinBounds(x, y)) {
                shipGrid[x][y] = ship.getValue();
            } else {
                throw new IllegalArgumentException(String.format("Attempted to build a board with a ship of out bounds! %s", coords));
            }
        }
    }

    @Override
    public Ship getShip(Coords coords) throws NoShipException, ActionOutOfBoundsException {
        final int x = coords.getX();
        final int y = coords.getY();
        if (!isWithinBounds(x, y)) {
            throw new ActionOutOfBoundsException(String.format("%s is out of bounds! Maximum size in either direction is %s.", coords, shipGrid.length));
        } else if (shipGrid[x][y] == null) {
            throw new NoShipException(String.format("There is no ship at %s", coords));
        } else {
            return shipGrid[x][y];
        }
    }

    @Override
    public void translateShipLocation(Coords oldCoords, Coords newCoords) throws NoShipException, ActionOutOfBoundsException, ShipCollisionException {
        final Ship ship = getShip(oldCoords);

        final int x = newCoords.getX();
        final int y = newCoords.getY();
        if (!isWithinBounds(x, y)) {
            throw new ActionOutOfBoundsException(String.format("Attempted ship move to %s is out of bounds! Maximum size in either direciton is %s.", newCoords, shipGrid.length));
        } else if (shipGrid[x][y] != null) {
            throw new ShipCollisionException(String.format("Cannot move a ship to %s as there is already a ship there!", newCoords));
        } else {
            shipGrid[x][y] = ship;
            shipGrid[oldCoords.getX()][oldCoords.getY()] = null;
        }
    }

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < shipGrid.length &&
                y >= 0 && y < shipGrid.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return Arrays.deepEquals(shipGrid, board.shipGrid);

    }

    @Override
    public int hashCode() {
        return shipGrid != null ? Arrays.deepHashCode(shipGrid) : 0;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        for (int x = 0; x < shipGrid.length; x++) {
            for (int y = 0; y < shipGrid[x].length; y++) {
                final Ship ship = shipGrid[x][y];
                if (ship != null) {
                    builder.append('(')
                            .append(x)
                            .append(", ")
                            .append(y)
                            .append(", ")
                            .append(ship.getOrientation().getShortCode())
                            .append(")");
                    if (ship.isSunk()) {
                        builder.append(" SUNK");
                    }
                    builder.append("\n");
                }
            }
        }

        return builder.toString();
    }
}
