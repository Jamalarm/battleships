package tomwoz.battleships.api.output;

import tomwoz.battleships.board.Board;

import java.io.Closeable;

/**
 * A generic interface for outputting the state of a GameController object. The test implementation is to simply dump the
 * contents to a Writer (usually a FileWriter), but this could easily be extended to implement outputting to a GUI, for
 * example.
 */
public interface IGameStateWriter extends Closeable {

    /**
     * Writers implementing this method should define some kind of output behaviour appropriate for the supplied
     * board object, such as outputting to file or console.
     * @param board The current board state of the invoking GameController object
     */
    void writeGameState(Board board);

}
