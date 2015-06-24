package tomwoz.battleships.moves.factories;

import tomwoz.battleships.api.IActionVisitor;
import tomwoz.battleships.board.Coords;
import tomwoz.battleships.moves.CompoundMoveAction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tomwoz.battleships.moves.CompoundMoveAction.*;

/**
 * Produces CompoundMoveAction objects from Regex Input
 */
public class CompoundMoveActionRegexFactory implements IRegexActionVisitorFactory {

    private static final Pattern PATTERN = Pattern.compile("^\\((\\d+)\\,\\s(\\d+)\\)\\s*([MRL]+)\\s*$");

    private final Map<String, Matcher> previousMatchers = new HashMap<>();

    @Override
    public boolean canBuild(String input) {
        return getMatcher(input).matches();
    }

    @Override
    public IActionVisitor fromString(String input) {
        final Matcher matcher = getMatcher(input);

        if (matcher.matches()) {
            final int x = Integer.parseInt(matcher.group(1));
            final int y = Integer.parseInt(matcher.group(2));

            final List<Instruction> instructions = parseInstructions(matcher.group(3));
            final Coords coords = new Coords(x, y);
            return new CompoundMoveAction(coords, instructions);
        } else {
            return null;
        }


    }

    private List<Instruction> parseInstructions(String instructionStr) {
        final LinkedList<Instruction> parsedInstructions = new LinkedList<>();

        for (int i = 0; i < instructionStr.length(); i++) {
            final char c = instructionStr.charAt(i);
            final Instruction instruction = Instruction.fromShortCode(c);

            if (instruction == null) {
                throw new IllegalArgumentException(String.format("Illegal character \'%s\' detected in input string!", c));
            } else {
                parsedInstructions.add(instruction);
            }
        }

        return parsedInstructions;
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
