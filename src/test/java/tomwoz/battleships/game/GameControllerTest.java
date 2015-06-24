package tomwoz.battleships.game;

import org.junit.Test;
import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.api.input.IActionInputStream;
import tomwoz.battleships.api.input.IBoardStateProvider;
import tomwoz.battleships.board.Board;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.ships.Orientation;
import tomwoz.battleships.ships.Ship;

import java.util.Collections;
import java.util.Map;

import static org.mockito.Mockito.*;

public class GameControllerTest {

    @Test
    public void testRun() {

        final IActionInputStream actionInputStream = mock(IActionInputStream.class);
        final IActionVisitor action = mock(IActionVisitor.class);
        when(actionInputStream.readNextAction()).thenReturn(action).thenReturn(null);

        final IBoardStateProvider boardStateProvider = mock(IBoardStateProvider.class);
        when(boardStateProvider.getBoardSize()).thenReturn(10);
        final Map<Coords, Ship> expectedShip = Collections.singletonMap(new Coords(1, 5), new Ship(Orientation.NORTH, true));
        when(boardStateProvider.getShips()).thenReturn(expectedShip);

        final GameController controller = new GameController(actionInputStream, boardStateProvider);

        final Board expectedBoard = new Board(10, expectedShip);

        controller.runGame();

        verify(action, times(1)).executeAction(expectedBoard);

    }

}