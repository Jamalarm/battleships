package tomwoz.battleships.moves.factories;

import org.junit.Test;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.CompoundMoveAction;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static tomwoz.battleships.moves.CompoundMoveAction.Instruction.MOVE_FORWARD;
import static tomwoz.battleships.moves.CompoundMoveAction.Instruction.TURN_LEFT;
import static tomwoz.battleships.moves.CompoundMoveAction.Instruction.TURN_RIGHT;

public class CompoundMoveActionRegexFactoryTest {

    @Test
    public void testCanBuild() throws Exception {
        final CompoundMoveActionRegexFactory factory = new CompoundMoveActionRegexFactory();

        //Valid cases
        assertTrue(factory.canBuild("(0, 10) MRLRLRM"));
        assertTrue(factory.canBuild("(100000000, 29999999) M"));

        //Invalid cases
        assertFalse(factory.canBuild("(9, 2)"));
        assertFalse(factory.canBuild(""));
        assertFalse(factory.canBuild("(9, 2)(9, 2)"));
        assertFalse(factory.canBuild("(0, 10) MRLRLRMT"));

    }

    @Test
    public void testFromString() throws Exception {
        final CompoundMoveActionRegexFactory factory = new CompoundMoveActionRegexFactory();

         Coords coords = new Coords(0, 0);
         LinkedList<CompoundMoveAction.Instruction> expectedInstructions = new LinkedList<>();
        expectedInstructions.add(MOVE_FORWARD);
        expectedInstructions.add(TURN_RIGHT);
        expectedInstructions.add(TURN_LEFT);
        expectedInstructions.add(MOVE_FORWARD);
        expectedInstructions.add(TURN_RIGHT);
        expectedInstructions.add(TURN_LEFT);
        expectedInstructions.add(MOVE_FORWARD);

        CompoundMoveAction expectedMove = new CompoundMoveAction(coords, expectedInstructions);

        assertEquals(expectedMove, factory.fromString("(0, 0) MRLMRLM"));

        coords = new Coords(999999999, 999999999);
        expectedInstructions = new LinkedList<>();
        expectedInstructions.add(MOVE_FORWARD);
        expectedInstructions.add(MOVE_FORWARD);
        expectedInstructions.add(TURN_LEFT);
        expectedInstructions.add(TURN_LEFT);
        expectedInstructions.add(TURN_RIGHT);
        expectedInstructions.add(TURN_RIGHT);

        expectedMove = new CompoundMoveAction(coords, expectedInstructions);

        assertEquals(expectedMove, factory.fromString("(999999999, 999999999) MMLLRR"));


    }
}