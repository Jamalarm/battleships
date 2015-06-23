package tomwoz.battleships.moves.factories;

import tomwoz.battleships.api.IMoveVisitor;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.CompoundLocomotionMove;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompoundLocomotionRegexFactory implements  IRegexMoveVisitorFactory {

    private static final Pattern PATTERN = Pattern.compile("^\\((\\d+)\\,\\s(\\d+)\\)\\s*([MRL]+)\\s*$");

    private final Map<String, Matcher> previousMatchers = new HashMap<>();

    @Override
    public boolean canBuild(String input) {
        return getMatcher(input).matches();
    }

    @Override
    public IMoveVisitor fromString(String input) {
        final Matcher matcher = getMatcher(input);

        if (matcher.matches()) {
            final int x = Integer.parseInt(matcher.group(1));
            final int y = Integer.parseInt(matcher.group(2));
            final String instructions = matcher.group(3);
            final Coords coords = new Coords(x, y);
            return new CompoundLocomotionMove(coords, instructions);
        } else {
            return null;
        }


    }

    private Matcher getMatcher(String input) {
        if (previousMatchers.containsKey(input)) {
            return previousMatchers.get(input);
        } else {
            final Matcher matcher = PATTERN.matcher(input);
            previousMatchers.put(input, matcher);
            return matcher;
        }
    }
}
