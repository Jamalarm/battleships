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

public class GameControllerFactory {

    public static GameController getFileBasedTestInstance(String filePath) throws FileNotFoundException {

        Reader fileReader = new FileReader(filePath);

        List<IRegexActionVisitorFactory> delegates = new LinkedList<>();
        delegates.add(new CompoundMoveActionRegexFactory());
        delegates.add(new SinkShipActionRegexFactory());

        IRegexActionVisitorFactory actionFactory = new MultiRegexActionVisitorFactory(delegates);

        final UnifiedTextBasedGameInputStream unifiedTextBasedGameInputStream = new UnifiedTextBasedGameInputStream(fileReader, actionFactory);

        return new GameController(unifiedTextBasedGameInputStream, unifiedTextBasedGameInputStream);
    }

}
