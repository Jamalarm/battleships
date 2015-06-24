package tomwoz.battleships.ships;

import tomwoz.battleships.board.Coords;

public enum Orientation {

    NORTH('N') {
        @Override
        public Coords moveForward(Coords coords) {
            return new Coords(coords.getX(), coords.getY() + 1);
        }

        @Override
        public Orientation turnRight() {
            return EAST;
        }

        @Override
        public Orientation turnLeft() {
            return WEST;
        }
    },


    SOUTH('S') {
        @Override
        public Coords moveForward(Coords coords) {
            return new Coords(coords.getX(), coords.getY() - 1);
        }

        @Override
        public Orientation turnRight() {
            return WEST;
        }

        @Override
        public Orientation turnLeft() {
            return EAST;
        }
    },


    EAST('E') {
        @Override
        public Coords moveForward(Coords coords) {
            return new Coords(coords.getX() + 1, coords.getY());
        }

        @Override
        public Orientation turnRight() {
            return SOUTH;
        }

        @Override
        public Orientation turnLeft() {
            return NORTH;
        }
    },


    WEST('W') {
        @Override
        public Coords moveForward(Coords coords) {
            return new Coords(coords.getX() - 1, coords.getY());
        }

        @Override
        public Orientation turnRight() {
            return NORTH;
        }

        @Override
        public Orientation turnLeft() {
            return SOUTH;
        }
    };

    private final char shortCode;

    Orientation(char shortCode) {
        this.shortCode = shortCode;
    }

    public static Orientation fromShortCode(char shortCode) {
        for (Orientation orientation : values()) {
            if (orientation.shortCode == shortCode) {
                return orientation;
            }
        }

        return null;
    }

    public abstract Coords moveForward(Coords coords);

    public abstract Orientation turnRight();

    public abstract Orientation turnLeft();

}
