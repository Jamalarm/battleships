package tomwoz.battleships.moves;

import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.api.IMoveVisitor;
import tomwoz.battleships.board.Coords;

public class SinkShipMove implements IMoveVisitor {

    private final Coords target;

    public SinkShipMove(Coords target) {
        this.target = target;
    }

    @Override
    public void executeMove(IBoard board) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SinkShipMove that = (SinkShipMove) o;

        return !(target != null ? !target.equals(that.target) : that.target != null);

    }

    @Override
    public int hashCode() {
        return target != null ? target.hashCode() : 0;
    }
}
