package chessfigureproductor.models.chess;

import javafx.util.Pair;

public interface ChessFigure {
    boolean matchRule (Pair<Integer, Integer> axisX, Pair<Integer, Integer> axisY);
    String getPictureName ();
}
