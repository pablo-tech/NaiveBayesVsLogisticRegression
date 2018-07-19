package com.onenow;



public class EstimatorBayesLaplasce extends EstimatorBayesAbstract implements EstimatorInterface {


    public EstimatorBayesLaplasce() {
        super();
    }

    @Override
    public void buildEstimate() {

        int rowCount = super.counter.getTotalRowCount();
        int cellCount = 4 * super.counter.inputVariableValueToOutputVariableValueCountTables.keySet().size();  // 4 * number of input vars
        super.estimationNormalizer = rowCount+cellCount;

        for(int i=0; i<super.counter.inputVariableValueToOutputVariableValueCountTables.keySet().size(); i++) {
            Qtable inputVarCount = super.counter.inputVariableValueToOutputVariableValueCountTables.get(i);
            Qtable inputVarEstimate = getInputVariableEstimate(inputVarCount, estimationNormalizer, i);
            super.inputVarValueToOutputVarValueEstimateTables.put(i, inputVarEstimate);
        }
    }

    private Qtable getInputVariableEstimate(Qtable inputVarCounts, int estimationNormalizer, int inputVariableId) {
        Qtable estimate = new Qtable();
        int laplascePrior = 1;
        // 4 cells for each inputVariableValue, with respect to each outputVariableValue
        estimate.setCountXzeroYzero(getCellEstimate(inputVarCounts.getValueXzeroYzero()+laplascePrior, estimationNormalizer));
        estimate.setCountXoneYzero(getCellEstimate(inputVarCounts.getValueXoneYzero()+laplascePrior, estimationNormalizer));
        estimate.setCountXzeroYone(getCellEstimate(inputVarCounts.getValueXzeroYone()+laplascePrior, estimationNormalizer));
        estimate.setCountXoneYone(getCellEstimate(inputVarCounts.getValueXoneYone()+laplascePrior, estimationNormalizer));
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
        return "LaplasceEstimation";
    }

}
