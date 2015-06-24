package tomwoz.battleships.inputStreams;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import tomwoz.battleships.api.input.IActionInputStream;
import tomwoz.battleships.api.input.IBoardStateProvider;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.factories.IRegexActionVisitorFactory;
import tomwoz.battleships.ships.Orientation;
import tomwoz.battleships.ships.Ship;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnifiedTextBasedGameInputStreamTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testReadNextAction() throws Exception {
        final IRegexActionVisitorFactory mockedFactory = mock(IRegexActionVisitorFactory.class);

        when(mockedFactory.canBuild(anyString())).thenReturn(true);
        when(mockedFactory.fromString(anyString())).thenReturn(null);

        IActionInputStream inputStream = new UnifiedTextBasedGameInputStream(new FileReader("src/test/resources/testStateInputFiles/testState1.txt"), mockedFactory);

        inputStream.readNextAction();

        verify(mockedFactory, times(1)).canBuild("(0, 0) MRMLMM");
        verify(mockedFactory, times(1)).fromString("(0, 0) MRMLMM");

        inputStream.readNextAction();

        verify(mockedFactory, times(1)).canBuild("(9, 2)");
        verify(mockedFactory, times(1)).fromString("(9, 2)");

        assertNull(inputStream.readNextAction());
    }

    @Test
    public void testGetBoardSize() throws Exception {
        IBoardStateProvider stateProvider = new UnifiedTextBasedGameInputStream(new FileReader("src/test/resources/testStateInputFiles/testState1.txt"), null);
        assertEquals(10, stateProvider.getBoardSize());

        stateProvider = new UnifiedTextBasedGameInputStream(new FileReader("src/test/resources/testStateInputFiles/testState2.txt"), null);
        assertEquals(12321798, stateProvider.getBoardSize());
    }

    @Test
    public void testMalformedSizeLine() throws FileNotFoundException {
        expectedException.expect(IllegalStateException.class);
        new UnifiedTextBasedGameInputStream(new FileReader("src/test/resources/testStateInputFiles/testMalformedSize.txt"), null);
    }

    @Test
    public void testGetShips() throws Exception {
        IBoardStateProvider stateProvider = new UnifiedTextBasedGameInputStream(new FileReader("src/test/resources/testStateInputFiles/testState1.txt"), null);
        Map<Coords, Ship> expectedShips = new HashMap<>();
        //(0, 0, N) (9, 2, E)
        expectedShips.put(new Coords(0, 0), new Ship(Orientation.NORTH));
        expectedShips.put(new Coords(9, 2), new Ship(Orientation.EAST));
        assertEquals(expectedShips, stateProvider.getShips());

        stateProvider = new UnifiedTextBasedGameInputStream(new FileReader("src/test/resources/testStateInputFiles/testState2.txt"), null);
        expectedShips = new HashMap<>();
        //(300, 500, N) (23, 0, E) (7999, 2, E) (273, 2, E)
        expectedShips.put(new Coords(300, 500), new Ship(Orientation.NORTH));
        expectedShips.put(new Coords(23, 0), new Ship(Orientation.EAST));
        expectedShips.put(new Coords(7999, 2), new Ship(Orientation.EAST));
        expectedShips.put(new Coords(273, 2), new Ship(Orientation.EAST));
        assertEquals(expectedShips, stateProvider.getShips());

    }
}