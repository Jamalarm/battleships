package tomwoz.battleships.api.input;

import tomwoz.battleships.board.Coords;

import java.util.Set;

public interface IBoardStateProvider {

    int getBoardSize();

    Set<Coords> getShipLocations();

}
