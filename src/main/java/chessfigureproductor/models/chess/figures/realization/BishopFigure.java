package chessfigureproductor.models.chess.figures.realization;

import chessfigureproductor.models.chess.figures.ChessFigure;
import javafx.util.Pair;

public class BishopFigure implements ChessFigure {
    @Override
    public boolean matchRule(Pair<Integer, Integer> axisX, Pair<Integer, Integer> axisY) {
        double xIndex = Math.abs(axisX.getKey() - axisX.getValue());
        double yIndex = Math.abs(axisY.getKey() - axisY.getValue());

        return ruleMather(xIndex, yIndex);
    }

    private boolean ruleMather (double xIndex, double yIndex) {
        return (xIndex >= 1 && yIndex >= 1) && (xIndex/yIndex == 1);
    }

    @Override
    public String getPictureName() {
        return "bishop";
    }

    @Override
    public String toString() {
        return "The Bishop";
    }
}
