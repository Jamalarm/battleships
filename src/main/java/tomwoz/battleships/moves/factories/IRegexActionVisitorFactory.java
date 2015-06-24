package tomwoz.battleships.moves.factories;

import tomwoz.battleships.api.IActionVisitor;

public interface IRegexActionVisitorFactory {

    boolean canBuild(String input);

    IActionVisitor fromString(String input);

}
