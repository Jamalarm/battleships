package tomwoz.battleships.moves;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.CompoundMoveAction.Instruction;

import java.util.LinkedList;

import static tomwoz.battleships.moves.CompoundMoveAction.Instruction.MOVE_FORWARD;
import static tomwoz.battleships.moves.CompoundMoveAction.Instruction.TURN_LEFT;
import static tomwoz.battleships.moves.CompoundMoveAction.Instruction.TURN_RIGHT;

public class CompoundMoveActionTest {

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

        final CompoundMoveAction expectedMove = new CompoundMoveAction(coords, expectedInstructions);

        final CompoundMoveAction generatedMove = new CompoundMoveAction(coords, "MRLMRLM");

        Assert.assertEquals(expectedMove, generatedMove);

    }

    @Test
    public void testThrowErrorOnBadInputString() {

    }

}