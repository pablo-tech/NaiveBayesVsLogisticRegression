package com.onenow;


/**
 * Estimate is simply the number of times an inputVarValue->outputVarValue occurs, divided the number of rows
 */
public class EstimatorBayesMaxLikelihood extends EstimatorBayesAbstract implements EstimatorInterface {


    public EstimatorBayesMaxLikelihood() {
        super();
    }

    @Override
    public void buildEstimate() {

        super.estimationNormalizer = super.counter.getTotalRowCount();

        for(int i=0; i<super.counter.inputVariableValueToOutputVariableValueCountTables.keySet().size(); i++) {
            Qtable inputVarCount = super.counter.inputVariableValueToOutputVariableValueCountTables.get(i);
            Qtable inputVarEstimate = getInputVariableEstimate(inputVarCount, estimationNormalizer, i);
            super.inputVarValueToOutputVarValueEstimateTables.put(i, inputVarEstimate);
        }
    }

    private Qtable getInputVariableEstimate(Qtable inputVarCounts, int estimatorBasis, int inputVariableId) {
        Qtable estimate = new Qtable();
        estimate.setCountXzeroYzero(getCellEstimate(inputVarCounts.getValueXzeroYzero(), estimatorBasis));
        estimate.setCountXoneYzero(getCellEstimate(inputVarCounts.getValueXoneYzero(), estimatorBasis));
        estimate.setCountXzeroYone(getCellEstimate(inputVarCounts.getValueXzeroYone(), estimatorBasis));
        estimate.setCountXoneYone(getCellEstimate(inputVarCounts.getValueXoneYone(), estimatorBasis));
        estimate.setId(inputVariableId);
        estimate.setName(getName());
        // System.out.println(getName() + " BUILT_ESTIMATE " + estimate);
        return estimate;
    }


    @Override
    public Qtable getEstimate(Integer inputVariableId) {
        return super.inputVarValueToOutputVarValueEstimateTables.get(inputVariableId);
    }

    @Override
    public String getName() {
        return "MaximumLikelihoodEstimation";
    }

}
