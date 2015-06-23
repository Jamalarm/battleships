package tomwoz.battleships.api.input;

import tomwoz.battleships.api.IMoveVisitor;

public interface IMoveInputStream {

    IMoveVisitor readMove();

}
