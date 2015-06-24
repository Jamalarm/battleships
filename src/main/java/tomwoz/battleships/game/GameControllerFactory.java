package tomwoz.battleships.game;

import tomwoz.battleships.inputStreams.UnifiedTextBasedGameInputStream;
import tomwoz.battleships.moves.factories.CompoundMoveActionRegexFactory;
import tomwoz.battleships.moves.factories.IRegexActionVisitorFactory;
import tomwoz.battleships.moves.factories.MultiRegexActionVisitorFactory;
import tomwoz.battleships.moves.factories.SinkShipActionRegexFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * Basic Inversion of Control style dependency injection class. This could obviously be implemented in Spring if
 * necessary but that seemed a bit like overkill for the test :)
 */
public class GameControllerFactory {

    /**
     * Returns a basic file based GameController object
     * @param filePath The file location containing both the input state on the first two lines, together with 1 or more
     *                 action lines to be executed
     * @return A GameController object representing the initial state as supplied by the file
     * @throws FileNotFoundException If the file does not exist
     */
    public static GameController getFileBasedTestInstance(String filePath) throws FileNotFoundException {

        Reader fileReader = new FileReader(filePath);

        List<IRegexActionVisitorFactory> delegates = new LinkedList<>();
        //Currently, if you implemented a new IActionVisitor and Factory, you would need to add it here
        delegates.add(new CompoundMoveActionRegexFactory());
        delegates.add(new SinkShipActionRegexFactory());

        IRegexActionVisitorFactory actionFactory = new MultiRegexActionVisitorFactory(delegates);

        final UnifiedTextBasedGameInputStream unifiedTextBasedGameInputStream = new UnifiedTextBasedGameInputStream(fileReader, actionFactory);

        return new GameController(unifiedTextBasedGameInputStream, unifiedTextBasedGameInputStream);
    }

}
