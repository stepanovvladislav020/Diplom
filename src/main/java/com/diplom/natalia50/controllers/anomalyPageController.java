package com.diplom.natalia50.controllers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class anomalyPageController implements Initializable {

    @FXML
    public LineChart<String,Number> priceChart;
    @FXML
    public Button loadDataButt, selectFewButt;
    @FXML
    public ListView fileView;

    @Override
    public void initialize(URL location, ResourceBundle resources){}

    @FXML
    public void loadData(javafx.event.ActionEvent actionEvent) {
        priceChart.getData().clear();
        //XYChart.Series<String,Number>series=new XYChart.Series<String,Number>();
        final NumberAxis yAxis = new NumberAxis(0, 5000000, 1);
        final CategoryAxis xAxis = new CategoryAxis();
        XYChart.Series series = new XYChart.Series();
        try (CSVReader dataReader = new CSVReader(new FileReader("src/main/resources/assets/testSet2.csv"))) {
            String[] nextLine;
            while ((nextLine = dataReader.readNext()) != null) {
                String year = String.valueOf(nextLine[0]);
                double price = Double.parseDouble(nextLine[1]);
                series.getData().add(new XYChart.Data(year, price));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        priceChart.getData().addAll(series);
        series.setName("USD");
        priceChart.setAnimated(false);
        priceChart.setLegendVisible(false);
        System.out.println("Success");
    }

    public void selectFewFiles(javafx.event.ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null){
            for (int i = 0; i<selectedFiles.size(); i++){
                fileView.getItems().add(selectedFiles.get(i).getName());
            }
        } else {
            System.out.println("File is invalid");
        }
    }

}


