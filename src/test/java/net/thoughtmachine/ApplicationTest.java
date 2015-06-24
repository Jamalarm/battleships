package net.thoughtmachine;

import org.junit.Assert;
import org.junit.Test;
import tomwoz.battleships.api.output.IGameStateWriter;
import tomwoz.battleships.board.Board;
import tomwoz.battleships.game.GameController;
import tomwoz.battleships.game.GameControllerFactory;
import tomwoz.battleships.writers.GameStateFileWriter;

import java.io.*;

/**
 * Full stack integration tests - Uses Verifying writer implementation which essentially just checks the program
 * output against a file.
 */
public class ApplicationTest {

    @Test
    public void testBasic1() throws IOException {
        final GameController gameController = GameControllerFactory.getFileBasedTestInstance("src/test/resources/testStateInputFiles/testState1.txt");
        gameController.runGame();
        gameController.writeGameState(new VerifyingWriter("src/test/resources/testStateOutputFiles/testOutput1.txt"));
    }

    @Test
    public void testBasic2() throws IOException {
        final GameController gameController = GameControllerFactory.getFileBasedTestInstance("src/test/resources/testStateInputFiles/testState3.txt");
        gameController.runGame();
        gameController.writeGameState(new VerifyingWriter("src/test/resources/testStateOutputFiles/testOutput3.txt"));
    }

    @Test
    public void testAllActionsFailAndNothingMoves() throws IOException {
        final GameController gameController = GameControllerFactory.getFileBasedTestInstance("src/test/resources/testStateInputFiles/testState4.txt");
        gameController.runGame();
        gameController.writeGameState(new VerifyingWriter("src/test/resources/testStateOutputFiles/testOutput4.txt"));
    }

    @Test
    public void testEveryoneGetsSunkMuhahaha() throws IOException {
        final GameController gameController = GameControllerFactory.getFileBasedTestInstance("src/test/resources/testStateInputFiles/testState5.txt");
        gameController.runGame();
        gameController.writeGameState(new VerifyingWriter("src/test/resources/testStateOutputFiles/testOutput5.txt"));
    }

    private class VerifyingWriter implements IGameStateWriter {

        private final String expected;

        private VerifyingWriter(String expectedFilePath) throws IOException {

            final FileReader fileReader = new FileReader(expectedFilePath);
            final BufferedReader bufferedReader = new BufferedReader(fileReader);

            final StringBuilder builder = new StringBuilder();

            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line);
                builder.append("\n");
                line = bufferedReader.readLine();
            }

            expected = builder.toString();
        }


        @Override
        public void writeGameState(Board board) {
            Assert.assertEquals(expected, board.toString());
        }

        @Override
        public void close() throws IOException {

        }
    }
}
