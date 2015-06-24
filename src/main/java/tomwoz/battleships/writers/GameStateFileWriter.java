package tomwoz.battleships.writers;

import tomwoz.battleships.api.IBoard;
import tomwoz.battleships.api.IGameStateWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class GameStateFileWriter implements IGameStateWriter {

    private final BufferedWriter writer;

    public GameStateFileWriter(Writer writer) {
        this.writer = new BufferedWriter(writer);
    }

    @Override
    public void writeGameState(IBoard board) {
        try {
            writer.write(board.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
