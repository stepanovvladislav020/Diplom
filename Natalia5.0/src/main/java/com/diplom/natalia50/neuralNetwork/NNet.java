package com.diplom.natalia50.neuralNetwork;

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
import org.neuroph.util.data.norm.Normalizer;
import org.neuroph.util.data.norm.ZeroMeanNormalizer;
import java.lang.*;


public class NNet extends NeuralNetwork {
    private String dataFilePath;
    int maxIterations = 100000;
    public NeuralNetwork Natalia = NeuralNetwork.createFromFile("C:\\My_Projects\\Natalia5.0\\src\\main\\resources\\assets\\NataliaNeuralNetwork.nnet");
    public DataSet testSet = DataSet.createFromFile("C:\\My_Projects\\Natalia5.0\\src\\main\\resources\\assets\\testSet.csv",1,0,",");
    public DataSet trainingSet = DataSet.createFromFile("C:\\My_Projects\\Natalia5.0\\src\\main\\resources\\assets\\trainingSet.csv",1,1,",");

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
        ZeroMeanNormalizer normalizer = new ZeroMeanNormalizer(inputData);
        normalizer.normalize(inputData);
    }

    public void trainNNet(NeuralNetwork nnet, BackPropagation backPropagation){
        this.setLearningRule(backPropagation);
        ((LMS) nnet.getLearningRule()).setMaxError(0.001);//0-1
        ((LMS) nnet.getLearningRule()).setLearningRate(0.7);//0-1
        ((LMS) nnet.getLearningRule()).setMaxIterations(maxIterations);//0-1
        NormalizeData(trainingSet);
        Natalia.learn(trainingSet);
    }
}
