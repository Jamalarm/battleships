package tomwoz.battleships.moves;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.CompoundLocomotionMove.Instruction;

import java.util.LinkedList;

import static tomwoz.battleships.moves.CompoundLocomotionMove.Instruction.MOVE_FORWARD;
import static tomwoz.battleships.moves.CompoundLocomotionMove.Instruction.TURN_LEFT;
import static tomwoz.battleships.moves.CompoundLocomotionMove.Instruction.TURN_RIGHT;

public class CompoundLocomotionMoveTest {

    @Rule
    ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCreateFromString() {
        final Coords coords = new Coords(0, 0);
        final LinkedList<Instruction> expectedInstructions = new LinkedList<>();
        expectedInstructions.add(MOVE_FORWARD);
        expectedInstructions.add(TURN_RIGHT);
        expectedInstructions.add(TURN_LEFT);
        expectedInstructions.add(MOVE_FORWARD);
        expectedInstructions.add(TURN_RIGHT);
        expectedInstructions.add(TURN_LEFT);
        expectedInstructions.add(MOVE_FORWARD);

        final CompoundLocomotionMove expectedMove = new CompoundLocomotionMove(coords, expectedInstructions);

        final CompoundLocomotionMove generatedMove = new CompoundLocomotionMove(coords, "MRLMRLM");

        Assert.assertEquals(expectedMove, generatedMove);

    }

    @Test
    public void testThrowErrorOnBadInputString() {

    }

}