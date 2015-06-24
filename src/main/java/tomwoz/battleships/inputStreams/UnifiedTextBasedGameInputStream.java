package tomwoz.battleships.inputStreams;

import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.api.input.IActionInputStream;
import tomwoz.battleships.api.input.IBoardStateProvider;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.factories.IRegexActionVisitorFactory;
import tomwoz.battleships.ships.Orientation;
import tomwoz.battleships.ships.Ship;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnifiedTextBasedGameInputStream implements IActionInputStream, IBoardStateProvider {

    private static Pattern BOARD_SIZE_REGEX = Pattern.compile("^\\s*(\\d+)\\s*$");
    private static Pattern INITIAL_SHIP_REGEX = Pattern.compile("(\\(\\d+\\,\\s\\d+\\,\\s[NSEW]\\))");
    private static Pattern SHIP_DELIMITATION_REGEX = Pattern.compile("^\\((\\d+)\\,\\s(\\d+)\\,\\s([NSEW])\\)$");

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
            final String line = this.reader.readLine();
            final Matcher matcher = BOARD_SIZE_REGEX.matcher(line);

            if (matcher.matches()) {
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
        return null;
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
