package tomwoz.battleships.api;

import tomwoz.battleships.board.Coords;

public interface IShip {

    void turnLeft();

    void turnRight();

    Coords moveForward();

    void sink();

    boolean isSunk();

}
