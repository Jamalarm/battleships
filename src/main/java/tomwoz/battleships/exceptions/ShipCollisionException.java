package tomwoz.battleships.exceptions;

/**
 * Thrown when a ship move is requested, but the target move location is already occupied by another ship. Ouch.
 */
public class ShipCollisionException extends Exception {

    public ShipCollisionException(String message) {
        super(message);
    }

}
