package tomwoz.battleships.api;

import tomwoz.battleships.board.Coords;
import tomwoz.battleships.ships.Ship;

public interface IBoard {

    Ship getShip(Coords coords) throws IndexOutOfBoundsException;

    void translateShipLocation(Coords oldCoords, Coords newCoords) throws IndexOutOfBoundsException, IllegalArgumentException;

}
