package tomwoz.battleships.moves.factories;

import tomwoz.battleships.api.IMoveVisitor;

public class MultiRegexMoveVisitorFactory implements IRegexMoveVisitorFactory {

    @Override
    public boolean canBuild(String input) {
        return false;
    }

    @Override
    public IMoveVisitor fromString(String input) {
        return null;
    }
}
