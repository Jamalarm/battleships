package tomwoz.battleships.ships;

import tomwoz.battleships.board.Coords;

public enum Direction {

    NORTH('N') {
        @Override
        public Coords moveForward(Coords coords) {
            return null;
        }

        @Override
        public Direction turnRight() {
            return EAST;
        }

        @Override
        public Direction turnLeft() {
            return WEST;
        }
    },


    SOUTH('S') {
        @Override
        public Coords moveForward(Coords coords) {
            return null;
        }

        @Override
        public Direction turnRight() {
            return WEST;
        }

        @Override
        public Direction turnLeft() {
            return EAST;
        }
    },


    EAST('E') {
        @Override
        public Coords moveForward(Coords coords) {
            return null;
        }

        @Override
        public Direction turnRight() {
            return SOUTH;
        }

        @Override
        public Direction turnLeft() {
            return NORTH;
        }
    },


    WEST('W') {
        @Override
        public Coords moveForward(Coords coords) {
            return null;
        }

        @Override
        public Direction turnRight() {
            return NORTH;
        }

        @Override
        public Direction turnLeft() {
            return SOUTH;
        }
    };

    private final char shortCode;

    Direction(char shortCode) {
        this.shortCode = shortCode;
    }

    public abstract Coords moveForward(Coords coords);

    public abstract Direction turnRight();

    public abstract Direction turnLeft();

    public static Direction fromShortCode(char shortCode) {
        for (Direction direction : values()) {
            if (direction.shortCode == shortCode) {
                return direction;
            }
        }

        return null;
    }

}
