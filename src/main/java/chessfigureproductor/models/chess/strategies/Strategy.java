package chessfigureproductor.models.chess.strategies;

import java.util.List;

public interface Strategy {
    int chooseRule (List<Integer> rules);
}
