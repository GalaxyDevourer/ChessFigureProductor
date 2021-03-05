package chessfigureproductor.models.chess.figures;

import chessfigureproductor.models.table.FigureStepData;
import chessfigureproductor.models.utils.csv.CSVManager;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MovementProcessor implements CSVManager {
    private final ChessFigure figure;
    private final int boardSize;
    private final Boolean isWrite;
    private final String path;

    private List<FigureStepData> generatedRules;

    public MovementProcessor(ChessFigure figure, Integer boardSize, Boolean isWrite, String path) {
        this.figure = figure;
        this.boardSize = boardSize;
        this.isWrite = isWrite;
        this.path = path;
    }

    public void start () {
        generatedRules = generateRules();
        if (isWrite) writeToCSV(generatedRules, path);
    }

    private List<FigureStepData> generateRules () {
        List<FigureStepData> rules = new ArrayList<>();
        int [][] board = generateTable();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                addRules(rules, board, i, j);
            }
        }

        return rules;
    }

    private int[][] generateTable () {
        int pos = 0;

        int [][] board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ++pos;
            }
        }

        return board;
    }

    private void addRules (List<FigureStepData> rules, int [][] board, int x, int y) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (figure.matchRule(new Pair<>(i, x), new Pair<>(j, y))) {
                    rules.add(new FigureStepData(rules.size()+1, board[x][y], board[i][j]));
                }
            }
        }
    }

    public List<FigureStepData> getGeneratedRules() {
        return generatedRules;
    }

}
