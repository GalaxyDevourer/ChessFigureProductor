package chessfigureproductor.controllers;

import chessfigureproductor.models.chess.figures.ChessFigure;
import chessfigureproductor.models.chess.figures.MovementProcessor;
import chessfigureproductor.models.chess.figures.realization.*;
import chessfigureproductor.models.chess.strategies.RoutingProcessor;
import chessfigureproductor.models.chess.strategies.Strategy;
import chessfigureproductor.models.chess.strategies.realization.BreadthStrategy;
import chessfigureproductor.models.chess.strategies.realization.DepthStrategy;
import chessfigureproductor.models.chess.strategies.realization.RandomStrategy;
import chessfigureproductor.models.table.FigureStepData;
import chessfigureproductor.models.table.RoutingStepData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainController {
    @FXML ChoiceBox<ChessFigure> figureChooser;
    @FXML Spinner<Integer> sizeChooser;
    @FXML ImageView pictureField;

    @FXML CheckBox exportCheck;
    @FXML TextField pathField;
    @FXML Button folderChooser;

    @FXML TableView<FigureStepData> stepTable;
    @FXML TableColumn<FigureStepData, Integer> ruleColumn;
    @FXML TableColumn<FigureStepData, Integer> currentColumn;
    @FXML TableColumn<FigureStepData, Integer> destColumn;

    @FXML ChoiceBox<Strategy> strategyChooser;
    @FXML Spinner<Integer> positionChooser;
    @FXML CheckBox reverseCheck;
    @FXML TableView<RoutingStepData> routingTable;
    @FXML TableColumn<RoutingStepData, Integer> numberColumn;
    @FXML TableColumn<RoutingStepData, Integer> stepColumn;

    @FXML Button startCalculating;

    @FXML void initialize () {
        sizeChooser.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 100, 8));
        positionChooser.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, (int) Math.pow(sizeChooser.getValue(), 2), 1));

        pathField.setText("E:\\_ИНСТИТУТ\\3 КУРС\\Второй семестр\\Інженерія знань\\Практика\\Лаб 4\\data");
        exportCheck.setSelected(true);

        figureChooser.setItems(FXCollections.observableArrayList(Arrays.asList(new BishopFigure(), new KingFigure(),
                new KnightFigure(), new PawnFigure(), new QueenFigure(), new RookFigure())));
        figureChooser.getSelectionModel().select(0);

        pictureField.setImage(new Image(String.valueOf(getClass().getResource("/pictures/" + figureChooser.getValue().getPictureName() + ".png"))));

        strategyChooser.setItems(FXCollections.observableArrayList(Arrays.asList(new BreadthStrategy(), new DepthStrategy(), new RandomStrategy())));
        strategyChooser.getSelectionModel().select(0);

        figureChooser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                pictureField.setImage(new Image(String.valueOf(getClass().getResource("/pictures/" + figureChooser.getValue().getPictureName() + ".png"))));
            }
        });

        ruleColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        currentColumn.setCellValueFactory(new PropertyValueFactory<>("current"));
        destColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));

        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        stepColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
    }

    @FXML void setFolderChooser () {
        File file = new DirectoryChooser().showDialog(pathField.getScene().getWindow());
        pathField.setText(file.getAbsolutePath());
    }

    @FXML void startCalculating () {
        MovementProcessor movementProcessor = new MovementProcessor(figureChooser.getValue(), sizeChooser.getValue(), exportCheck.isSelected(), pathField.getText());
        movementProcessor.start();

        List<FigureStepData> rules = movementProcessor.getGeneratedRules();
        stepTable.setItems(FXCollections.observableArrayList(rules));

        Integer initStep = (reverseCheck.isSelected()) ? positionChooser.getValue() : 1;
        RoutingProcessor routingProcessor = new RoutingProcessor(strategyChooser.getValue(), rules, initStep, reverseCheck.isSelected());
        routingProcessor.start();
        routingTable.setItems(FXCollections.observableArrayList(routingProcessor.getRoutingData()));
    }

    @FXML void positionChooserSetValue () {
        positionChooser.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, (int) Math.pow(sizeChooser.getValue(), 2), 1));
    }

    @FXML void positionChooserShow () {
        positionChooser.setVisible(reverseCheck.isSelected());
    }

}