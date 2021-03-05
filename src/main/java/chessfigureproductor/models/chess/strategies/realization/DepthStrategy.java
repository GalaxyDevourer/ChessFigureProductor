package chessfigureproductor.models.chess.strategies.realization;

import chessfigureproductor.models.chess.strategies.Strategy;

import java.util.List;

public class DepthStrategy implements Strategy {
    @Override
    public int chooseRule(List<Integer> rules) {
        return rules.get(rules.size()-1);
    }

    @Override
    public String toString() {
        return "Depth Strategy";
    }
}