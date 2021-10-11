package com.diplom.natalia50.neuralNetwork;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.input.InputFunction;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.nnet.Perceptron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.LMS;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.data.norm.MaxMinNormalizer;
import org.neuroph.util.data.norm.Normalizer;
import org.neuroph.util.data.norm.RangeNormalizer;
import org.neuroph.util.data.norm.ZeroMeanNormalizer;
import java.lang.*;


public class NNet extends NeuralNetwork {
    private String dataFilePath;
    int maxIterations = 10000;
    public NeuralNetwork Natalia = NeuralNetwork.createFromFile("src\\main\\resources\\assets\\NataliaNeuralNetwork.nnet");
    public DataSet testSet = DataSet.createFromFile("src\\main\\resources\\assets\\testSet1.csv",1,0,",");
    public DataSet trainingSet = DataSet.createFromFile("src\\main\\resources\\assets\\trainingSet.csv",1,1,",");

    public void  DetectAnomaly(DataSet inputData, String dataFilePath){
        inputData = DataSet.createFromFile(dataFilePath,inputData.getInputSize(),inputData.getOutputSize(),",");
        NormalizeData(inputData);
        for (DataSetRow dataRow : inputData.getRows()){
            Natalia.setInput(dataRow.getInput());
            Natalia.calculate();
            double[] networkOutput = Natalia.getOutput();
            System.out.println("Input: " + Arrays.toString(dataRow.getInput()));
            System.out.println("Output: " + Arrays.toString(networkOutput));
        }
    }

    public void NormalizeData(DataSet inputData){
        ZeroMeanNormalizer normalizer = new ZeroMeanNormalizer(inputData); // Нормализация средним показывает себя преимущественно хуже
        //RangeNormalizer normalizer = new RangeNormalizer(0,1); //Если есть число выходных нейронов, которые отмечаются в строке 28, то все работает
        //MaxMinNormalizer normalizer = new MaxMinNormalizer(inputData);
        normalizer.normalize(inputData);
        File temp = new File("src/main/resources/assets/temp.csv");
        try
        {
            boolean created = temp.createNewFile();
            if(created){
                inputData.saveAsTxt("src/main/resources/assets/temp.csv", ",");
                System.out.println("File has been created");
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("File name: " + temp.getName());
        if(temp.exists()){
            inputData.saveAsTxt("src/main/resources/assets/temp.csv", ",");
            System.out.println("File exists");
        }
        else
            System.out.println("File not found");
    }

    public void trainNNet(BackPropagation learningRule){
        this.setLearningRule(learningRule);
        ((LMS) Natalia.getLearningRule()).setMaxError(0.001);//0-1
        ((LMS) Natalia.getLearningRule()).setLearningRate(0.2);//0-1
        ((LMS) Natalia.getLearningRule()).setMaxIterations(maxIterations);//0-1
        NormalizeData(trainingSet);
        Natalia.learn(trainingSet);
        Natalia.save("src/main/resources/assets/NataliaNeuralNetwork.nnet");
        //предположение: после тренировки надо ее сохранять обратно в файл
    }
}
