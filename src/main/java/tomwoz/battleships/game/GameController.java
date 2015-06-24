package tomwoz.battleships.game;

import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.api.input.IActionInputStream;
import tomwoz.battleships.api.input.IBoardStateProvider;
import tomwoz.battleships.board.Board;

import java.io.IOException;

public final class GameController {

    private final IActionInputStream actionInputStream;

    private final IBoard board;

    public GameController(IActionInputStream actionInputStream, IBoardStateProvider boardStateProvider) {
        this.actionInputStream = actionInputStream;
        this.board = new Board(boardStateProvider.getBoardSize(), boardStateProvider.getShips());
    }

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
}
