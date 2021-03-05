package chessfigureproductor.models.chess.strategies.realization;

import chessfigureproductor.models.chess.strategies.Strategy;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {
    @Override
    public int chooseRule(List<Integer> rules) {
        return rules.get(getRandomRule(rules.size()-1));
    }

    private int getRandomRule(int max) {
        return new Random().nextInt(max);
    }

    @Override
    public String toString() {
        return "Random Strategy";
    }
}