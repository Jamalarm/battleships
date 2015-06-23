package tomwoz.battleships.api;

public interface IMoveVisitor {

    void executeMove(IBoard board);

}
