package tomwoz.battleships.moves;

import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.board.Board;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.exceptions.ActionOutOfBoundsException;
import tomwoz.battleships.exceptions.NoShipException;
import tomwoz.battleships.exceptions.ShipCollisionException;
import tomwoz.battleships.ships.Orientation;
import tomwoz.battleships.ships.Ship;

import java.util.List;

/**
 * A IActionVisitor that will take a startingCoordinate, attempt to find a ship there, then apply a series of translations
 * to it:
 *
 * M - Move forward one unit
 * L - Turn Left
 * R - Turn Right
 *
 * If the set of moves turn out to be valid, then the Ships location and orientation will be updated
 */
public class CompoundMoveAction implements IActionVisitor {

    private final Coords startingCoords;
    private final List<Instruction> moveInstructions;

    public CompoundMoveAction(Coords startingCoords, List<Instruction> moveInstructions) {
        this.startingCoords = startingCoords;
        this.moveInstructions = moveInstructions;
    }

    @Override
    public void executeAction(Board board) {

        try {
            final Ship ship = board.getShip(startingCoords);

            if (!ship.isSunk()) {
                Orientation orientation = ship.getOrientation();
                Coords newPosition = startingCoords;

                //Step through each instruction and apply it
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

                //This call will throw an error if we're making a invalid move
                board.translateShipLocation(startingCoords, newPosition);
                //Must call this second, as the board checking logic needs to execute first
                ship.setOrientation(orientation);
            } else {
                System.out.println("Compound Move Action failed as the ship is already sunk! It can't move!");
            }
        } catch (NoShipException e) {
            System.out.println("Compound Move Action failed as there was no ship at specified Coords!");
        } catch (ActionOutOfBoundsException e) {
            System.out.println("Compound Move Action failed: " + e.getMessage());
        } catch (ShipCollisionException e) {
            System.out.println("Compound Move Action failed due to a ship already occupying the final move location! ");
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
