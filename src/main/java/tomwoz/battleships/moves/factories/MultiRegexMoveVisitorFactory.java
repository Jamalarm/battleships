package tomwoz.battleships.moves.factories;

import tomwoz.battleships.api.IMoveVisitor;

import java.util.Arrays;
import java.util.List;

public class MultiRegexMoveVisitorFactory implements IRegexMoveVisitorFactory {

    private final List<IRegexMoveVisitorFactory> delegates;

    public MultiRegexMoveVisitorFactory(List<IRegexMoveVisitorFactory> delegates) {
        this.delegates = delegates;
    }


    @Override
    public boolean canBuild(String input) {

        for (IRegexMoveVisitorFactory delegate : delegates) {
            if (delegate.canBuild(input)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public IMoveVisitor fromString(String input) {

        for (IRegexMoveVisitorFactory delegate : delegates) {
            if (delegate.canBuild(input)) {
                return delegate.fromString(input);
            }
        }

        return null;
    }
}
