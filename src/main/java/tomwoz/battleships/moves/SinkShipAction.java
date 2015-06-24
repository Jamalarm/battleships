package tomwoz.battleships.moves;

import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.ships.Ship;

public class SinkShipAction implements IActionVisitor {

    private final Coords target;

    public SinkShipAction(Coords target) {
        this.target = target;
    }

    @Override
    public void executeAction(IBoard board) {
        final Ship ship = board.getShip(target);

        if (ship != null) {
            ship.setSunk(true);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SinkShipAction that = (SinkShipAction) o;

        return !(target != null ? !target.equals(that.target) : that.target != null);

    }

    @Override
    public int hashCode() {
        return target != null ? target.hashCode() : 0;
    }
}
