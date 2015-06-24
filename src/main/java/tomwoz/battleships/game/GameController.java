package tomwoz.battleships.game;

import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.api.input.IActionInputStream;
import tomwoz.battleships.api.input.IBoardStateProvider;

public class GameController {

    private final IActionInputStream actionInputStream;

    private final IBoard board;

    public GameController(IActionInputStream actionInputStream, IBoardStateProvider boardStateProvider) {
        this.actionInputStream = actionInputStream;
        this.board = buildBoard(boardStateProvider);
    }

    private IBoard buildBoard(IBoardStateProvider boardStateProvider) {
        return null;
    }
}
