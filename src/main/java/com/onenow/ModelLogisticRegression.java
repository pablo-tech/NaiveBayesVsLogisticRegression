package com.onenow;

/**
 * “batch” gradient algorithm
 */
public class ModelLogisticRegression {

    private Dataset dataset;
    private EstimatorLogistic estimator;
    private PredictionLogistic predictor;


    public ModelLogisticRegression(Dataset dataset,
                                   EstimatorLogistic estimator,
                                   PredictionLogistic predictor) {
        this.dataset = dataset;
        this.estimator = estimator;
        this.predictor = predictor;
    }

    public void build() {
        estimator.setDataset(dataset);
        estimator.buildEstimate();
    }

    public String getAccuracy(Dataset testDataset) {
        String annotation = "Estimator: " + estimator.toString() + "\n";
        return ModelMetrics.getAccuracy(testDataset, predictor, annotation);
    }


}
