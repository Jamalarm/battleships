package tomwoz.battleships.ships;

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
}
