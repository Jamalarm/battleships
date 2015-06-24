package tomwoz.battleships.moves.factories;

import tomwoz.battleships.api.IActionVisitor;

interface IRegexActionVisitorFactory {

    boolean canBuild(String input);

    IActionVisitor fromString(String input);

}
