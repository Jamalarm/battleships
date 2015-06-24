package tomwoz.battleships.ships;

public class Ship {

    private Direction direction;
    private boolean sunk;

    public Ship(Direction direction, boolean sunk) {
        this.direction = direction;
        this.sunk = sunk;
    }

    public Ship(Direction direction) {
        this(direction, false);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }
}
