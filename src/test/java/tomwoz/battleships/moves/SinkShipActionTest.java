package tomwoz.battleships.moves;

import org.junit.Test;
import tomwoz.battleships.board.Board;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.ships.Ship;

import static org.mockito.Mockito.*;

public class SinkShipActionTest {

    @Test
    public void testSinkShip() throws Exception {
        final Coords coords = new Coords(10, 10);
        final SinkShipAction sinkShipAction = new SinkShipAction(coords);

        final Board mockedBoard = mock(Board.class);
        final Ship mockedShip = mock(Ship.class);
        when(mockedBoard.getShip(coords)).thenReturn(mockedShip);

        sinkShipAction.executeAction(mockedBoard);

        verify(mockedBoard, times(1)).getShip(coords);
        verify(mockedShip, times(1)).setSunk(true);
    }
}