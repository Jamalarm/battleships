package tomwoz.battleships.api.input;

import tomwoz.battleships.board.Coords;
import tomwoz.battleships.ships.Ship;

import java.util.Map;
import java.util.Set;

public interface IBoardStateProvider {

    int getBoardSize();

    Map<Coords, Ship> getShips();

}
