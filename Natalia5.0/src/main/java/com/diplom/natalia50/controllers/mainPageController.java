package com.diplom.natalia50.controllers;

import com.diplom.natalia50.NataliaApplication;
import com.diplom.natalia50.neuralNetwork.NNet;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.javafx.sg.prism.NGImageView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.skin.SplitPaneSkin;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class mainPageController implements Initializable {
    private double xOffset;
    private double yOffset;
    @FXML
    public LineChart<String,Number> priceChart;
    @FXML
    public Button loadDataButt;
    @FXML
    public Circle btnClose;
    @FXML
    public Pane anomalyDetectButt;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void appClose(javafx.scene.input.MouseEvent mouseEvent){
        if (mouseEvent.getSource()==btnClose){
            System.out.println("Success");
           Platform.exit(); //здесь вопрос по поводу корректности моих действий
        }
    }

    @FXML
    public void openAnomalyWindow(javafx.scene.input.MouseEvent mouseEvent) throws Exception{
        if (mouseEvent.getSource() == anomalyDetectButt){
            Parent rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/anomalyPage.fxml")));
            Stage stage = new Stage();
            Scene scene = new Scene(rootNode);
            stage.setScene(scene);
            stage.show();
            System.out.println("Success");
        }
    }
}
