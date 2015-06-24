package tomwoz.battleships.api.input;

import tomwoz.battleships.api.IActionVisitor;

public interface IActionInputStream {

    IActionVisitor readNextAction();

}
