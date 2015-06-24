package tomwoz.battleships.exceptions;

/**
 * Exception indicating that an action has been requested that in some way goes outside of the Board's bounds, and
 * therefore out of the valid area of play
 */
public class ActionOutOfBoundsException extends Exception {

    public ActionOutOfBoundsException(String message) {
        super(message);
    }
}
