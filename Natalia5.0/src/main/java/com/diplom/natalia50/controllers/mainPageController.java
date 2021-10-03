package com.diplom.natalia50.controllers;

import com.diplom.natalia50.NataliaApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class mainPageController implements Initializable {

    @FXML
    public LineChart<String,Number> priceChart;
    @FXML
    public Button loadDataButt, btnClose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void loadData(javafx.event.ActionEvent actionEvent) {
        priceChart.getData().clear();
        XYChart.Series<String,Number>series=new XYChart.Series<String,Number>();
        series.getData().add(new XYChart.Data<String,Number>("Jan",70));
        series.getData().add(new XYChart.Data<String,Number>("Feb",71));
        series.getData().add(new XYChart.Data<String,Number>("Mar",72));
        series.getData().add(new XYChart.Data<String,Number>("Apr",73));
        series.getData().add(new XYChart.Data<String,Number>("May",400));
        series.getData().add(new XYChart.Data<String,Number>("jun",72));
        series.getData().add(new XYChart.Data<String,Number>("Jul",71));
        series.getData().add(new XYChart.Data<String,Number>("Aug",767));
        series.getData().add(new XYChart.Data<String,Number>("Sep",77));
        series.getData().add(new XYChart.Data<String,Number>("Oct",78));
        series.getData().add(new XYChart.Data<String,Number>("Nov",-1));
        series.getData().add(new XYChart.Data<String,Number>("Dec",71));
        series.setName("USD");
        priceChart.getData().add(series);
        System.out.println("Success");
    }

    @FXML
    public void appClose(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getSource()==btnClose){

        }
    }
}
