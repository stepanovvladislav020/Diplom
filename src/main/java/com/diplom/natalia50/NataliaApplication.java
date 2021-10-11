package com.diplom.natalia50;

import com.diplom.natalia50.controllers.mainPageController;
import com.diplom.natalia50.neuralNetwork.NNet;
import javafx.application.Application;
import javafx.scene.control.ToolBar;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.LMS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.*;
import java.io.File;

/*
--module-path
C:\My_Projects\javafx-sdk-17.0.0.1\lib
--add-modules
javafx.controls,javafx.fxml,javafx.media,javafx.base,javafx.graphics
--add-exports
javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
*/

@SpringBootApplication
public class NataliaApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private Parent rootNode;
    private double xOffset;
    private double yOffset;
    private static final String cssMainPage = "src/main/resources/style/mainPageStyle.css";

    public static void main(String[] args) {

        Application.launch(NataliaApplication.class,args);
        System.out.println("Testing NN...");
        NNet Natalia = new NNet();//здесь вопрос по поводу правильности моих действий
        BackPropagation learningRule = new BackPropagation();
        //Natalia.trainNNet(learningRule);
        DataSet inputData = new NNet().testSet;
        Natalia.DetectAnomaly(inputData, inputData.getFilePath());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.getScene().getStylesheets().add(new File(cssMainPage).toURI().toURL().toExternalForm());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        rootNode.getScene().setFill(Color.TRANSPARENT);
        primaryStage.show();
        rootNode.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        rootNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }
    @Override
    public void stop() throws Exception{
        applicationContext.close();
        Platform.exit();
    }
    @Override
    public void init() throws Exception{
        applicationContext = new SpringApplicationBuilder(NataliaApplication.class).run();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainPage.fxml"));
        rootNode = fxmlLoader.load();
    }

}
