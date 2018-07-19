package com.onenow;


import java.util.HashMap;

/**
 * Use a valueTupleCounter of input/output values, use an countEstimator to turn counts into estimates, then get probability using estimates
 */
public class ModelNaiveBayesClassifier {

    private Dataset trainingSet;
    private CounterBayesian valueTupleCounter;
    private EstimatorBayesAbstract countEstimator;
    private PredictionBayesArgumentMaximizer predictionMaximizer;

    public ModelNaiveBayesClassifier(Dataset trainingSet,
                                     EstimatorBayesAbstract countEstimator,
                                     PredictionBayesArgumentMaximizer predictionMaximizer) {
        this.trainingSet = trainingSet;
        this.valueTupleCounter = new CounterBayesian(trainingSet);
        this.countEstimator = countEstimator;
        countEstimator.setCounter(valueTupleCounter);
        this.predictionMaximizer = predictionMaximizer;
    }

    public void build() {
        valueTupleCounter.countInputVarToOutput();
        valueTupleCounter.countOutputVar();
        countEstimator.buildEstimate();
    }

    public String getAccuracy(Dataset testDataset) {
        String annotation = "Estimator: " + countEstimator.getName() + " with Normalization count="+countEstimator.getEstimationNormalizer() + "\n";
        return ModelMetrics.getAccuracy(testDataset, predictionMaximizer, annotation);
    }


    public Qtable getInputToOutputCountTable(int inputVarId) {
        return this.valueTupleCounter.inputVariableValueToOutputVariableValueCountTables.get(inputVarId);
    }

    public HashMap<Boolean, Integer> getOutputTable() {
        return this.valueTupleCounter.outputVariableValueCountTable;
    }


    public Dataset getTrainingSet() {
        return trainingSet;
    }

    public EstimatorBayesAbstract getCountEstimator() {
        return countEstimator;
    }

    public PredictionBayesArgumentMaximizer getPredictionMaximizer() {
        return predictionMaximizer;
    }
}