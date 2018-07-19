package com.onenow;

import org.testng.annotations.Test;

import java.util.HashMap;

public class ModelLogisticRegressionTest {


    private static final String DATASET_PATH = "/Users/pablo/git/JunoMoneta/Learn/Mehran/data";


    @Test
    // a. Use learning rate η = 0.0001
    // should be able to achieve 100% classification accuracy on the testing data
    public void simple() {

        double learningRate = 0.0001;
        long numEpochs = 10000;
        runModel("simple", new EstimatorLogistic(learningRate, numEpochs));

    }

    @Test
    // b. Use learning rate η = 0.0001
    // should be able to achieve at least 90% classification accuracy on the testing data
    // using a model trained with Maximum Likelihood Estimators.
    public void vote() {

        double learningRate = 0.0001;
        long numEpochs = 10000;
        runModel("vote", new EstimatorLogistic(learningRate, numEpochs));

    }

    @Test
    // c. Use learning rate η = 0.0001
    public void heart() {

        double learningRate = 0.0001;
        long numEpochs = 10000;
        runModel("heart", new EstimatorLogistic(learningRate, numEpochs));

    }

    @Test
    // d. As a starting point for experimenting, try η = 0.0005 and η = 0.00002
    // (i.e., values for η that are larger and smaller than 0.0001 by a factor of 5),
    // and then continue experimenting from there.
    public void heartLearning() {

        long numEpochs = 10000;

        for(double learningRate=0.001; learningRate>=0.00000001; learningRate=learningRate/5) {
            runModel("heart", new EstimatorLogistic(learningRate, numEpochs));
        }
    }

    private void runModel(String keyword, EstimatorLogistic estimator) {
        DatasetReader reader = new DatasetReader(DATASET_PATH);
        Dataset trainingDataset = reader.get(keyword, false);
        Dataset testDataset = reader.get(keyword, true);
        PredictionLogistic predictionLogistic = new PredictionLogistic(estimator);
        ModelLogisticRegression model = new ModelLogisticRegression(trainingDataset, estimator, predictionLogistic);
        model.build();
        int inputVarid = 0;
        // System.out.println(trainingDataset.toString());
        System.out.println(estimator.toString());
        DataRow row0 = testDataset.getDataRows().get(0);
        System.out.println("PREDICTION_FIRST_ROW="+ predictionLogistic.getPrediction(row0));
        System.out.println(model.getAccuracy(testDataset));
    }

    private String getOutputString(HashMap<Boolean, Integer> outputCounts) {
        String s = "\n";
        s = s + "\tcount(y=0)="+outputCounts.get(false) + "\n";
        s = s + "\tcount(y=1)="+outputCounts.get(true);
        return s;
    }
}
