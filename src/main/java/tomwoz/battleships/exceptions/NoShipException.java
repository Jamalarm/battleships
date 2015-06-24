package tomwoz.battleships.exceptions;

/**
 * To be thrown when a Ship is not found at specified Coordinates
 */
public class NoShipException extends Exception {
    public NoShipException(String message) {
        super(message);
    }
}
