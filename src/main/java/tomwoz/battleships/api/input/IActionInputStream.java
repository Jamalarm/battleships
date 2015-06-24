package tomwoz.battleships.api.input;

import tomwoz.battleships.api.IActionVisitor;

import java.io.Closeable;

/**
 * A provider of a stream of IActionVisitor objects. This could be implemented in any number of ways, such as a
 * command line input method, or a keyboard listener method. For the purposes of the test, there is only one
 * implementation, UnifiedTextBasedGameInputStream
 */
public interface IActionInputStream extends Closeable {

    /**
     * Returns the next IActionVisitor object to be executed on the game state.
     * This method should block if continuous input is required, such as waiting for user input or similar.
     * @return An IActionVisitor object representing the next action to be executed, or null if the end of the stream
     * is reached
     */
    IActionVisitor readNextAction();

}
