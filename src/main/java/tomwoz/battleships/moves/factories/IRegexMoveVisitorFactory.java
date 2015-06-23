package tomwoz.battleships.moves.factories;

import tomwoz.battleships.api.IMoveVisitor;

interface IRegexMoveVisitorFactory {

    boolean canBuild(String input);

    IMoveVisitor fromString(String input);

}
