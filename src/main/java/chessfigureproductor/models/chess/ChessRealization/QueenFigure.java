package chessfigureproductor.models.chess.ChessRealization;

import chessfigureproductor.models.chess.ChessFigure;
import javafx.util.Pair;

public class QueenFigure implements ChessFigure {
    @Override
    public boolean matchRule(Pair<Integer, Integer> axisX, Pair<Integer, Integer> axisY) {
        double xIndex = Math.abs(axisX.getKey() - axisX.getValue());
        double yIndex = Math.abs(axisY.getKey() - axisY.getValue());

        return ruleMather(xIndex, yIndex);
    }

    private boolean ruleMather (double xIndex, double yIndex) {
        return ((xIndex >= 1 && yIndex >= 1) && (xIndex/yIndex == 1)) || ((xIndex == 0 && yIndex >= 1) || (xIndex >= 1 && yIndex == 0));
    }

    @Override
    public String getPictureName() {
        return "queen";
    }

    @Override
    public String toString() {
        return "The Queen";
    }
}
