package tomwoz.battleships.moves;

import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.board.Coords;

import java.util.LinkedList;
import java.util.List;

public class CompoundMoveAction implements IActionVisitor {

    private final Coords startingCoords;
    private final List<Instruction> moveInstructions;

    public CompoundMoveAction(Coords startingCoords, String instructionStr) {
        this.startingCoords = startingCoords;
        this.moveInstructions = parseInstructions(instructionStr);
    }

    public CompoundMoveAction(Coords startingCoords, List<Instruction> moveInstructions) {
        this.startingCoords = startingCoords;
        this.moveInstructions = moveInstructions;
    }

    private List<Instruction> parseInstructions(String instructionStr) {

        final LinkedList<Instruction> parsedInstructions = new LinkedList<Instruction>();

        for (int i = 0; i < instructionStr.length(); i++) {
            final char c = instructionStr.charAt(i);
            final Instruction instruction = Instruction.fromShortCode(c);

            if (instruction == null) {
                throw new IllegalArgumentException(String.format("Illegal character \'%s\' detected in input string!", c));
            } else {
                parsedInstructions.add(instruction);
            }
        }

        return parsedInstructions;
    }

    @Override
    public void executeAction(IBoard board) {
        //Rememeber to check if the ship is alive
    }

    enum Instruction {
        MOVE_FORWARD('M'),
        TURN_RIGHT('R'),
        TURN_LEFT('L');

        final char shortCode;

        Instruction(char shortCode) {
            this.shortCode = shortCode;
        }

        static Instruction fromShortCode(char shortCode) {
            for (Instruction instruction : values()) {
                if (instruction.shortCode == shortCode) {
                    return instruction;
                }
            }

            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompoundMoveAction that = (CompoundMoveAction) o;

        if (startingCoords != null ? !startingCoords.equals(that.startingCoords) : that.startingCoords != null)
            return false;
        return !(moveInstructions != null ? !moveInstructions.equals(that.moveInstructions) : that.moveInstructions != null);

    }

    @Override
    public int hashCode() {
        int result = startingCoords != null ? startingCoords.hashCode() : 0;
        result = 31 * result + (moveInstructions != null ? moveInstructions.hashCode() : 0);
        return result;
    }
}
