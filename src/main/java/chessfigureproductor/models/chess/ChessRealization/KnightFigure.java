package chessfigureproductor.models.chess.ChessRealization;

import chessfigureproductor.models.chess.ChessFigure;
import javafx.util.Pair;

public class KnightFigure implements ChessFigure {
    @Override
    public boolean matchRule(Pair<Integer, Integer> axisX, Pair<Integer, Integer> axisY) {
        double xIndex = Math.abs(axisX.getKey() - axisX.getValue());
        double yIndex = Math.abs(axisY.getKey() - axisY.getValue());

        return ruleMather(xIndex, yIndex);
    }

    private boolean ruleMather (double xIndex, double yIndex) {
        return (xIndex == 1 && yIndex == 2) || (xIndex == 2 && yIndex == 1);
    }

    @Override
    public String getPictureName() {
        return "knight";
    }

    @Override
    public String toString() {
        return "The Knight";
    }
}
