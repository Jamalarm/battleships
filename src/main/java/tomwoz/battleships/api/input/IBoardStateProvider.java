package tomwoz.battleships.api.input;

import tomwoz.battleships.board.Coords;
import tomwoz.battleships.ships.Ship;

import java.util.Map;
import java.util.Set;

/**
 * A data provider class that is used to instantiate GameController. It should provide a board size and a set of initial
 * ships. The test implementation is to read the first two lines of in the input file as specified, but this could
 * be extended to define a game state from anything, such as command line user input, or even some kind of GUI interaction
 */
public interface IBoardStateProvider {

    /**
     * Returns the desired board size for this GameController object.
     * @return an int representing the desired board size
     */
    int getBoardSize();

    /**
     * A method to provide a list of initial ships to be added to the board
     * @return A map consisting of a Ship value object, and a Coords key object representing where it should be placed
     * on the board
     */
    Map<Coords, Ship> getShips();

}
