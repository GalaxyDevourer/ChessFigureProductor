package chessfigureproductor.models.chess.strategies;

import chessfigureproductor.models.table.FigureStepData;
import chessfigureproductor.models.table.RoutingStepData;

import java.util.*;

public class RoutingProcessor {
    private Strategy strategy;
    private Integer initStep;
    private Boolean reverseMode;
    private List<FigureStepData> figureData;

    private List<RoutingStepData> routingData;

    public RoutingProcessor(Strategy strategy, List<FigureStepData> figureData, Integer initStep, Boolean reverseMode) {
        this.strategy = strategy;
        this.initStep = initStep;
        this.reverseMode = reverseMode;
        this.figureData = figureData;
    }

    public void start () {
        HashMap<Integer, List<Integer>> figureMap = figureMapper();
        List<Integer> routing = mapProcessor(figureMap, initStep);

        if (reverseMode) Collections.reverse(routing);
        routingData = routingMapper(routing);
    }

    // get final routing values
    private List<Integer> mapProcessor (HashMap<Integer, List<Integer>> map, Integer initStep) {
        List<Integer> routing = new ArrayList<>();
        routing.add(initStep);
        int iterations = map.size() - 1;

        int chosenStep;
        for (int i = 0, step = initStep; i < iterations; i++, step = chosenStep) {
            List<Integer> rules = map.get(step); // get steps from rule (list of rules)
            HashMap<Integer, Integer> rulesMap = new LinkedHashMap<>(); // create map<step, sub steps>

            rules.forEach(x -> {
                if (!routing.contains(x)) {
                    rulesMap.put(x, getUniqSize(map.get(x), routing));
                }
            });
            System.out.println("rules map = " + rulesMap);
            if (rulesMap.isEmpty()) break;

            chosenStep = chosenNextStep(chosenRules(rulesMap));
            System.out.println("chosen step = " + chosenStep);
            routing.add(chosenStep);
            System.out.println("routing = " + routing);
        }

        return routing;
    }

    private Integer getUniqSize (List<Integer> list, List<Integer> routing) {
       return (int) list.stream().filter(x -> !routing.contains(x)).count();
    }

    private Integer chosenNextStep (List<Integer> chosenRules) {
        int step;

        if (chosenRules.size() > 1) {
            step = strategy.chooseRule(chosenRules);
        }
        else step = chosenRules.get(0);

        return step;
    }

    private List<Integer> chosenRules (HashMap<Integer, Integer> rulesMap) {
        List<Integer> chosenRules = new ArrayList<>();
        int min = Objects.requireNonNull(rulesMap.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).orElse(null)).getValue();
        System.out.println("min value = " + min);

        rulesMap.forEach( (x, y) -> {
            if (reverseMode) chosenRules.add(x);
            else if (y == min) chosenRules.add(x);
        });

        return chosenRules;
    }

    private HashMap<Integer, List<Integer>> figureMapper () {
        HashMap<Integer, List<Integer>> map = new LinkedHashMap<>();

        figureData.forEach( x -> {
            if (map.containsKey(x.getCurrent())) {
                map.get(x.getCurrent()).add(x.getDestination());
            }
            else map.put(x.getCurrent(), new ArrayList<>(Arrays.asList(x.getDestination())));
        });

        map.forEach((x,y) -> System.out.println(x + " " + y));
        System.out.println("----------------------------");

        return map;
    }

    private List<RoutingStepData> routingMapper (List<Integer> steps) {
        List<RoutingStepData> list = new ArrayList<>();

        for (int i = 1; i < steps.size() + 1; i++) {
            list.add(new RoutingStepData(i, steps.get(i-1)));
        }

        return list;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Integer getInitStep() {
        return initStep;
    }

    public void setInitStep(Integer initStep) {
        this.initStep = initStep;
    }

    public Boolean getReverseMode() {
        return reverseMode;
    }

    public void setReverseMode(Boolean reverseMode) {
        this.reverseMode = reverseMode;
    }

    public List<FigureStepData> getFigureData() {
        return figureData;
    }

    public void setFigureData(List<FigureStepData> figureData) {
        this.figureData = figureData;
    }

    public List<RoutingStepData> getRoutingData() {
        return routingData;
    }

    public void setRoutingData(List<RoutingStepData> routingData) {
        this.routingData = routingData;
    }
}
