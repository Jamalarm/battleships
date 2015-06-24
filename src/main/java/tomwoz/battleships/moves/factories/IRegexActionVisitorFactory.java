package tomwoz.battleships.moves.factories;

import tomwoz.battleships.api.IActionVisitor;

/**
 * A factory object that produces IActionVisitor objects from string inputs
 */
public interface IRegexActionVisitorFactory {

    /**
     * Checks the supplied string against the internal regex and returns whether or not it matches
     * @param input A string representing the object to be built
     * @return true if the string matches and an object can be built
     */
    boolean canBuild(String input);

    /**
     * Builds the IActionVisitor object from the inputString
     * @param input A string representing the object to be built
     * @return an IActionVisitor objected parsed from the input string, or null if the string does not match
     */
    IActionVisitor fromString(String input);

}
