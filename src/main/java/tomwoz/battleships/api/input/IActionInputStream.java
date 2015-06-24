package tomwoz.battleships.api.input;

import tomwoz.battleships.api.IActionVisitor;

import java.io.Closeable;

public interface IActionInputStream extends Closeable {

    IActionVisitor readNextAction();

}
