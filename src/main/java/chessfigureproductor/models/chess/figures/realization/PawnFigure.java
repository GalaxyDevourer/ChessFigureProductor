package chessfigureproductor.models.chess.figures.realization;

import chessfigureproductor.models.chess.figures.ChessFigure;
import javafx.util.Pair;

public class PawnFigure implements ChessFigure {
    @Override
    public boolean matchRule(Pair<Integer, Integer> axisX, Pair<Integer, Integer> axisY) {
        double xIndex = Math.abs(axisX.getKey() - axisX.getValue());
        double yIndex = Math.abs(axisY.getKey() - axisY.getValue());

        return ruleMather(xIndex, yIndex);
    }

    private boolean ruleMather (double xIndex, double yIndex) {
        return (xIndex == 1 && yIndex == 0);
    }

    @Override
    public String getPictureName() {
        return "pawn";
    }

    @Override
    public String toString() {
        return "The Pawn";
    }
}
