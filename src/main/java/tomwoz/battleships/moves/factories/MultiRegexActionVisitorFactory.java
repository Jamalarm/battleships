package tomwoz.battleships.moves.factories;

import tomwoz.battleships.api.IActionVisitor;

import java.util.List;

public class MultiRegexActionVisitorFactory implements IRegexActionVisitorFactory {

    private final List<IRegexActionVisitorFactory> delegates;

    public MultiRegexActionVisitorFactory(List<IRegexActionVisitorFactory> delegates) {
        this.delegates = delegates;
    }


    @Override
    public boolean canBuild(String input) {

        for (IRegexActionVisitorFactory delegate : delegates) {
            if (delegate.canBuild(input)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public IActionVisitor fromString(String input) {

        for (IRegexActionVisitorFactory delegate : delegates) {
            if (delegate.canBuild(input)) {
                return delegate.fromString(input);
            }
        }

        return null;
    }
}
