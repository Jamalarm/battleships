package tomwoz.battleships.inputStreams;

import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.api.input.IActionInputStream;
import tomwoz.battleships.api.input.IBoardStateProvider;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.factories.IRegexActionVisitorFactory;
import tomwoz.battleships.ships.Orientation;
import tomwoz.battleships.ships.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Test implementation of the IActionInputStream, IBoardStateProvider. This implements both interfaces as, in the test
 * brief, both the initial state and actions will be in a single file.
 *
 * This class makes heavy use of regular expressions. The initial state is parsed during the construction of the object
 * the actions are not parsed until they are requested via the GetNextAction operation
 */
public class UnifiedTextBasedGameInputStream implements IActionInputStream, IBoardStateProvider {

    private static final Pattern BOARD_SIZE_REGEX = Pattern.compile("^\\s*(\\d+)\\s*$");
    private static final Pattern INITIAL_SHIP_REGEX = Pattern.compile("(\\(\\d+\\,\\s\\d+\\,\\s[NSEW]\\))");
    private static final Pattern SHIP_DELIMITATION_REGEX = Pattern.compile("^\\((\\d+)\\,\\s(\\d+)\\,\\s([NSEW])\\)$");

    private final BufferedReader reader;
    private final IRegexActionVisitorFactory actionVisitorFactory;

    private final int cachedBoardSize;
    private final Map<Coords, Ship> cachedInitialShips;

    public UnifiedTextBasedGameInputStream(Reader reader, IRegexActionVisitorFactory actionVisitorFactory) {
        this.actionVisitorFactory = actionVisitorFactory;
        this.reader = new BufferedReader(reader);

        //Order here is important - the first line should be the size and the second the ships
        this.cachedBoardSize = parseBoardSize();
        this.cachedInitialShips = parseInitialShips();
    }

    private int parseBoardSize() {

        try {
            //Check if this line is appropriately formatted for board size
            final String line = this.reader.readLine();
            final Matcher matcher = BOARD_SIZE_REGEX.matcher(line);

            if (matcher.matches()) {
                //Get the board size and return
                return Integer.parseInt(matcher.group(1));
            } else {
                throw new IllegalStateException(String.format("Board size line is malformed! \"%s\"", line));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    private Map<Coords, Ship> parseInitialShips() {
        Map<Coords, Ship> initialShips = new HashMap<>();

        try {
            final String line = this.reader.readLine();
            final Matcher matcher = INITIAL_SHIP_REGEX.matcher(line);

            if (matcher.find()) {
                matcher.reset();

                while (matcher.find()) {
                    //Submatcher is used to pick out the individual values from a individual ship representation (x, y, orientation) etc
                    final String shipRepresentation = matcher.group(1);
                    final Matcher subMatcher = SHIP_DELIMITATION_REGEX.matcher(shipRepresentation);

                    if (subMatcher.matches()) {
                        final int x = Integer.parseInt(subMatcher.group(1));
                        final int y = Integer.parseInt(subMatcher.group(2));
                        final Orientation orientation = Orientation.fromShortCode(subMatcher.group(3).charAt(0));

                        initialShips.put(new Coords(x, y), new Ship(orientation));

                    } else {
                        throw new IllegalStateException(String.format("Initial ship entry is malformed! \"%s\"", shipRepresentation));
                    }
                }

            } else {
                throw new IllegalStateException(String.format("Initial ships line is malformed! \"%s\"", line));
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return initialShips;
    }

    @Override
    public IActionVisitor readNextAction() {
        try {
            final String line = reader.readLine();

            if (line == null) {
                return null;
            } else if (!actionVisitorFactory.canBuild(line)) {
                throw new IllegalArgumentException(String.format("Badly formatted action! Cannot parse to ActionVisitor! (%s)", line));
            } else {
                return actionVisitorFactory.fromString(line);
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public int getBoardSize() {
        return this.cachedBoardSize;
    }

    @Override
    public Map<Coords, Ship> getShips() {
        return this.cachedInitialShips;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
