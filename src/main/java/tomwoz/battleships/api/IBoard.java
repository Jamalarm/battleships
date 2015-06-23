package tomwoz.battleships.api;

import tomwoz.battleships.board.Coords;

public interface IBoard {

    IShip getShip(Coords coords);

    void setShipLocation(Coords coords);

}
