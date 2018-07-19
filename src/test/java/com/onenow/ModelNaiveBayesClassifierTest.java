package com.onenow;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 *
 */
public class ModelNaiveBayesClassifierTest {

    private static final String DATASET_PATH = "/Users/pablo/git/JunoMoneta/Learn/Mehran/data";


    @Test
    public void testVsTrain() throws Exception {

        DatasetReader reader = new DatasetReader(DATASET_PATH);

        Dataset trainSet = reader.get("vote", false);
        System.out.println("FOUND_TRAIN_DATASET="+trainSet);
        Assert.assertTrue(trainSet.getPath().contains("train"));
        Assert.assertTrue(trainSet.getDataRows().size()==300);
        Assert.assertTrue(trainSet.getDataRows().get(0).getInputX().size()==48);

        Dataset testSet = reader.get("vote", true);
        System.out.println("FOUND_TEST_DATASET="+testSet);
        Assert.assertTrue(testSet.getPath().contains("test"));
        Assert.assertTrue(testSet.getDataRows().size()==135);
        Assert.assertTrue(testSet.getDataRows().get(0).getInputX().size()==48);

    }

    @Test
    // a. should be able to achieve 100% classification accuracy on the testing data
    // using a model trained with Maximum Likelihood Estimators
    public void simple() {

        // Max Likelihood
        runModel("simple", new EstimatorBayesMaxLikelihood());

        // Laplasce
        runModel("simple", new EstimatorBayesLaplasce());

    }

    @Test
    // b. should be able to achieve at least 90% classification accuracy on the testing data
    // using a model trained with Maximum Likelihood Estimators.
    public void vote() {

        // Max Likelihood
        runModel("vote", new EstimatorBayesMaxLikelihood());

        // Laplasce
        runModel("vote", new EstimatorBayesLaplasce());

    }

    @Test
    // c. heart
    public void heart() {

        // Max Likelihood
        runModel("heart", new EstimatorBayesMaxLikelihood());

        // Laplasce
        runModel("heart", new EstimatorBayesLaplasce());

    }

    private void runModel(String keyword, EstimatorBayesAbstract estimator) {
        DatasetReader reader = new DatasetReader(DATASET_PATH);
        Dataset trainingDataset = reader.get(keyword, false);
        Dataset testDataset = reader.get(keyword, true);
        PredictionBayesArgumentMaximizer predictionBayesArgumentMaximizer = new PredictionBayesArgumentMaximizer(estimator);
        ModelNaiveBayesClassifier model = new ModelNaiveBayesClassifier(trainingDataset, estimator, predictionBayesArgumentMaximizer);
        model.build();
        int inputVarid = 0;
        // System.out.println(trainingDataset.toString());
        System.out.println("INPUT_VAR_TO_OUTPUT_COUNT="+model.getInputToOutputCountTable(inputVarid));
        System.out.println("OUTPUT_TABLE="+getOutputString(model.getOutputTable()));
        System.out.println("INPUT_VAR_TO_OUTPUT_ESTIMATE="+model.getCountEstimator().getEstimate(inputVarid));
        DataRow row0 = model.getTrainingSet().getDataRows().get(0);
        System.out.println("PREDICTION_FIRST_ROW="+ model.getPredictionMaximizer().getPrediction(row0));
        System.out.println(model.getAccuracy(testDataset));
    }

    private String getOutputString(HashMap<Boolean, Integer> outputCounts) {
        String s = "\n";
        s = s + "\tcount(y=0)="+outputCounts.get(false) + "\n";
        s = s + "\tcount(y=1)="+outputCounts.get(true);
        return s;
    }

}