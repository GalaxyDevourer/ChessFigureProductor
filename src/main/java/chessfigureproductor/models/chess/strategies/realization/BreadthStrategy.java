package chessfigureproductor.models.chess.strategies.realization;

import chessfigureproductor.models.chess.strategies.Strategy;

import java.util.List;

public class BreadthStrategy implements Strategy {
    @Override
    public int chooseRule(List<Integer> rules) {
        return rules.get(0);
    }

    @Override
    public String toString() {
        return "Breadth Strategy";
    }
}