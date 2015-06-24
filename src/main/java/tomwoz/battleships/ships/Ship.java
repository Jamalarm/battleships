package tomwoz.battleships.ships;

/**
 * Mutable object representing a ship. The ship currently only has knowledge of two things, where it's facing and
 * whether or not it's been sunk
 */
public class Ship {

    private Orientation orientation;
    private boolean sunk;

    public Ship(Orientation orientation, boolean sunk) {
        this.orientation = orientation;
        this.sunk = sunk;
    }

    public Ship(Orientation orientation) {
        this(orientation, false);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (sunk != ship.sunk) return false;
        return orientation == ship.orientation;

    }

    @Override
    public int hashCode() {
        int result = orientation != null ? orientation.hashCode() : 0;
        result = 31 * result + (sunk ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Facing " + orientation.name() + ", sunk=" + sunk;
    }
}
