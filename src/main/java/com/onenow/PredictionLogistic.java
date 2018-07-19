package com.onenow;


public class PredictionLogistic implements PredictionMaximizerInterface {


    private EstimatorLogistic estimator;


    public PredictionLogistic(EstimatorLogistic estimator) {
        this.estimator = estimator;
    }

    public Boolean getPrediction(DataRow row) {
        double probabilityThreshold = 0.5;
        if(estimator.getProbabilityY1(row)>probabilityThreshold) {
            return true;
        }
        return false;
    }

}
