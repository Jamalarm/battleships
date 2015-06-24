package net.thoughtmachine;

import tomwoz.battleships.game.GameController;
import tomwoz.battleships.game.GameControllerFactory;
import tomwoz.battleships.writers.GameStateFileWriter;

import java.io.*;

public class Application {

    public static void main(String[] args) throws IOException {
        final GameController controller = GameControllerFactory.getFileBasedTestInstance("src/test/resources/testStateInputFiles/testState1.txt");

        controller.runGame();

        controller.writeGameState(new GameStateFileWriter(new FileWriter("target/testout.txt")));
    }
}
