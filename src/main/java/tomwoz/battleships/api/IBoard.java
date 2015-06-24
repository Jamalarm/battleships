package tomwoz.battleships.api;

import tomwoz.battleships.board.Coords;
import tomwoz.battleships.exception.ActionOutOfBoundsException;
import tomwoz.battleships.exception.NoShipException;
import tomwoz.battleships.ships.Ship;

public interface IBoard {

    Ship getShip(Coords coords) throws NoShipException, ActionOutOfBoundsException;

    void translateShipLocation(Coords oldCoords, Coords newCoords) throws NoShipException, ActionOutOfBoundsException;

}
