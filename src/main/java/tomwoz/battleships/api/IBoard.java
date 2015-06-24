package tomwoz.battleships.api;

import tomwoz.battleships.board.Coords;
import tomwoz.battleships.exceptions.ActionOutOfBoundsException;
import tomwoz.battleships.exceptions.NoShipException;
import tomwoz.battleships.exceptions.ShipCollisionException;
import tomwoz.battleships.ships.Ship;

public interface IBoard {

    Ship getShip(Coords coords) throws NoShipException, ActionOutOfBoundsException;

    void translateShipLocation(Coords oldCoords, Coords newCoords) throws NoShipException, ActionOutOfBoundsException, ShipCollisionException;

}
