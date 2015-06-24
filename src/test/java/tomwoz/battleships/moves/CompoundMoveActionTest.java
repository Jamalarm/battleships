package tomwoz.battleships.moves;

import org.junit.Assert;
import org.junit.Test;
import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.CompoundMoveAction.Instruction;
import tomwoz.battleships.ships.Orientation;
import tomwoz.battleships.ships.Ship;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static tomwoz.battleships.moves.CompoundMoveAction.Instruction.*;

public class CompoundMoveActionTest {

    @Test
    public void testExecuteActionSuccessfully() throws Exception {
        final Coords startingCoords = new Coords(0, 0);
        final Ship ship = new Ship(Orientation.NORTH);

        final IBoard mockedBoard = mock(IBoard.class);
        when(mockedBoard.getShip(startingCoords)).thenReturn(ship);


        List<Instruction> instructions = new LinkedList<>();
        instructions.add(MOVE_FORWARD);
        instructions.add(MOVE_FORWARD);
        instructions.add(TURN_RIGHT);
        instructions.add(MOVE_FORWARD);
        instructions.add(MOVE_FORWARD);
        instructions.add(TURN_LEFT);
        instructions.add(MOVE_FORWARD);
        instructions.add(MOVE_FORWARD);
        instructions.add(TURN_RIGHT);

        final CompoundMoveAction moveAction = new CompoundMoveAction(startingCoords, instructions);

        moveAction.executeAction(mockedBoard);

        verify(mockedBoard, times(1)).translateShipLocation(startingCoords, new Coords(2, 4));
        Assert.assertEquals(Orientation.EAST, ship.getOrientation());
    }

    @Test
    public void testExecuteActionSuccessfullyWithManyMoves() throws Exception {
        final Coords startingCoords = new Coords(1, 1);
        final Ship ship = new Ship(Orientation.NORTH);

        final IBoard mockedBoard = mock(IBoard.class);
        when(mockedBoard.getShip(startingCoords)).thenReturn(ship);


        List<Instruction> instructions = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            instructions.add(MOVE_FORWARD);
        }
        instructions.add(TURN_RIGHT);
        for (int i = 0; i < 100; i++) {
            instructions.add(MOVE_FORWARD);
        }

        final CompoundMoveAction moveAction = new CompoundMoveAction(startingCoords, instructions);

        moveAction.executeAction(mockedBoard);

        verify(mockedBoard, times(1)).translateShipLocation(startingCoords, new Coords(101, 101));
        Assert.assertEquals(Orientation.EAST, ship.getOrientation());
    }
}