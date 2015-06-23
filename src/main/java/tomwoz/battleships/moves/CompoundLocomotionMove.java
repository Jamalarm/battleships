package tomwoz.battleships.moves;

import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.api.IMoveVisitor;
import tomwoz.battleships.board.Coords;

import java.util.LinkedList;
import java.util.List;

public class CompoundLocomotionMove implements IMoveVisitor {

    private final Coords startingCoords;
    private final List<Instruction> instructions;

    public CompoundLocomotionMove(Coords startingCoords, String instructionStr) {
        this.startingCoords = startingCoords;
        this.instructions = parseInstructions(instructionStr);
    }

    public CompoundLocomotionMove(Coords startingCoords, List<Instruction> instructions) {
        this.startingCoords = startingCoords;
        this.instructions = instructions;
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
    public void executeMove(IBoard board) {

    }

    private enum Instruction {
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

}
