package com.onenow;

import java.util.HashMap;


/**
 * Turn counts in a counter into estimates
 */
public abstract class EstimatorBayesAbstract implements EstimatorInterface {

    protected CounterBayesian counter;
    protected int estimationNormalizer;

    // generated estimate
    protected HashMap<Integer, Qtable> inputVarValueToOutputVarValueEstimateTables;


    EstimatorBayesAbstract() {
        this.inputVarValueToOutputVarValueEstimateTables = new HashMap<>();
    }

    /**
     * It all boils down to this
     */
    protected double getCellEstimate(double count, double outOf) {
        return count/outOf;
    }

    /**
     * SET/GET
     */
    public void setCounter(CounterBayesian counter) {
        this.counter = counter;
    }

    public int getEstimationNormalizer() {
        return estimationNormalizer;
    }

    /**
     * ABSTRACT
     */

    // converts counts into estimate for all input variables
    abstract void buildEstimate();

    // gets estimate for an input variable
    abstract Qtable getEstimate(Integer inputVariableId);


}
