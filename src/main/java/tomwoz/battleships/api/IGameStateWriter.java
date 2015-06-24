package tomwoz.battleships.api;

import java.io.Closeable;

public interface IGameStateWriter extends Closeable {

    void writeGameState(IBoard board);

}
