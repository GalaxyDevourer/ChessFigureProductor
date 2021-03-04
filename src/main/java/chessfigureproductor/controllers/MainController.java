package chessfigureproductor.controllers;

import chessfigureproductor.models.chess.ChessFigure;
import chessfigureproductor.models.chess.ChessRealization.*;
import chessfigureproductor.models.chess.MovementProcessor;
import chessfigureproductor.models.table.FigureStepData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.util.Pair;

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

    @FXML Button startCalculating;

    private ChessFigure currentFigure;

    @FXML void initialize () {
        sizeChooser.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 100, 8));
        pathField.setText("E:\\_ИНСТИТУТ\\3 КУРС\\Второй семестр\\Інженерія знань\\Практика\\Лаб 4\\data");
        exportCheck.setSelected(true);

        pictureField.setImage(new Image(String.valueOf(getClass().getResource("/pictures/initial.png"))));

        figureChooser.setItems(FXCollections.observableArrayList(Arrays.asList(new BishopFigure(), new KingFigure(),
                new KnightFigure(), new PawnFigure(), new QueenFigure(), new RookFigure())));

        figureChooser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                currentFigure = figureChooser.getSelectionModel().getSelectedItem();

                pictureField.setImage(new Image(String.valueOf(getClass().getResource("/pictures/" + currentFigure.getPictureName() + ".png"))));
            }
        });

        ruleColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        currentColumn.setCellValueFactory(new PropertyValueFactory<>("current"));
        destColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
    }

    @FXML void setFolderChooser () {
        File file = new DirectoryChooser().showDialog(pathField.getScene().getWindow());
        pathField.setText(file.getAbsolutePath());
    }

    @FXML void startCalculating () {
        MovementProcessor processor = new MovementProcessor(currentFigure, sizeChooser.getValue(), exportCheck.isSelected(), pathField.getText());
        processor.start();

        List<FigureStepData> rules = processor.getGeneratedRules();
        stepTable.setItems(FXCollections.observableArrayList(rules));
    }

}
