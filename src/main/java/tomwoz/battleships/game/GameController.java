package tomwoz.battleships.game;

import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.api.output.IGameStateWriter;
import tomwoz.battleships.api.input.IActionInputStream;
import tomwoz.battleships.api.input.IBoardStateProvider;
import tomwoz.battleships.board.Board;

import java.io.IOException;

/**
 * Main game controller class. This class essentially represents the game as a whole. It was designed to be easily
 * extensible. By varying the IActionInputStream and IBoardStateProvider constructor parameters, you can wire this class
 * up to be controlled by many non-file based input schemes.
 *
 * The class covers instantiation of the initial game state, and sequential execution of the ActionVisitor objects,
 * together with the option of outputting the state of the game to a Writer if required.
 */
public final class GameController {

    private final IActionInputStream actionInputStream;

    private final Board board;

    /**
     * Builds a basic GameController object
     * @param actionInputStream A stream object supplying IActionVisitor objects to be executed against the game state
     * @param boardStateProvider A data provider object to be used for constructing the initial game state
     */
    public GameController(IActionInputStream actionInputStream, IBoardStateProvider boardStateProvider) {
        this.actionInputStream = actionInputStream;
        this.board = new Board(boardStateProvider.getBoardSize(), boardStateProvider.getShips());
    }

    /**
     * This method will contiuously poll the actionInputStream for actions to perform, and then apply them to the game
     * state. The method will return once the actionInputStream has returned null, and therefore signalled it has no
     * more action objects to apply
     */
    public void runGame() {
        try {
            IActionVisitor nextAction = actionInputStream.readNextAction();

            while (nextAction != null) {
                nextAction.executeAction(board);
                nextAction = actionInputStream.readNextAction();
            }

        } finally {
            try {
                actionInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Basic method that will pass the internal board to a Writer object for outputting
     * @param writer The desired writer object that the board will be passed to
     */
    public void writeGameState(IGameStateWriter writer) {
        try {
            writer.writeGameState(board);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
