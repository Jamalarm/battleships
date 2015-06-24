package tomwoz.battleships.moves;

import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.exception.ActionOutOfBoundsException;
import tomwoz.battleships.exception.NoShipException;
import tomwoz.battleships.ships.Orientation;
import tomwoz.battleships.ships.Ship;

import java.util.List;

public class CompoundMoveAction implements IActionVisitor {

    private final Coords startingCoords;
    private final List<Instruction> moveInstructions;

    public CompoundMoveAction(Coords startingCoords, List<Instruction> moveInstructions) {
        this.startingCoords = startingCoords;
        this.moveInstructions = moveInstructions;
    }

    @Override
    public void executeAction(IBoard board) {

        try {
            final Ship ship = board.getShip(startingCoords);

            if (ship != null && !ship.isSunk()) {
                Orientation orientation = ship.getOrientation();
                Coords newPosition = startingCoords;

                for (Instruction moveInstruction : moveInstructions) {

                    switch (moveInstruction) {
                        case MOVE_FORWARD:
                            newPosition = orientation.moveForward(newPosition);
                            break;
                        case TURN_LEFT:
                            orientation = orientation.turnLeft();
                            break;
                        case TURN_RIGHT:
                            orientation = orientation.turnRight();
                            break;
                    }

                }

                board.translateShipLocation(startingCoords, newPosition);
            }
        } catch (NoShipException e) {
            e.printStackTrace();
        } catch (ActionOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public enum Instruction {
        MOVE_FORWARD('M'),
        TURN_RIGHT('R'),
        TURN_LEFT('L');

        final char shortCode;

        Instruction(char shortCode) {
            this.shortCode = shortCode;
        }

        public static Instruction fromShortCode(char shortCode) {
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
