package tomwoz.battleships.api;

/**
 * An extensible representation of a game action using the Visitor pattern.
 *
 * Implementations should encapsulate all logic related to the ACTION (not board rule validation, which is elsewhere)
 * inside the executeAction method.
 *
 * There are only two implementations of this interface for the test:
 * CompoundMoveAction
 * SinkShipAction
 *
 * but this could easily be extended to add other types of actions if required, such as an action to fire an extra
 * powerful shell that sinks every ship in 'n' cells from the target
 */
public interface IActionVisitor {

    /**
     * Analogous to Visitor.visit(). This method should contain all the logic that should be performed when the desired
     * game action is invoked, such as moving a ship or firing a shell. The Visitor is expected to directly modify the
     * board object.
     * @param board The board object representing the current game state
     */
    void executeAction(IBoard board);

}
