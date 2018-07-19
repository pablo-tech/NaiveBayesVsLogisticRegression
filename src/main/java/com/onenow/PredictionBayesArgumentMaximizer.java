package com.onenow;

public class PredictionBayesArgumentMaximizer implements PredictionMaximizerInterface {


    private EstimatorBayesAbstract estimator;


    public PredictionBayesArgumentMaximizer(EstimatorBayesAbstract estimator) {
        this.estimator = estimator;
    }

    // gets probability for an input row
    public Boolean getPrediction(DataRow row) {

        // output = 0
        Double probabilityY0 = getYoutputProbability(false);
        Double fullJointLogProbabilityY0 = getLogProbability(row, false, probabilityY0) +
                getLogProbability(probabilityY0);

        // output = 1
        Double probabilityY1 = getYoutputProbability(true);
        Double fullJointProbabilityY1 = getLogProbability(row, true, probabilityY1) +
                getLogProbability(probabilityY1);

        Boolean outputPrediction;
        if(fullJointLogProbabilityY0>fullJointProbabilityY1) {
            outputPrediction=false; // predict zero
        } else {
            outputPrediction = true; // predict one
        }
        // System.out.println("row="+row + " predictedAs="+ outputPrediction + " fullJointLogProbabilityY0="+fullJointLogProbabilityY0 + " fullJointProbabilityY1="+fullJointProbabilityY1);

        return outputPrediction;
    }

    double getYoutputProbability(Boolean value) {
        Double totalRows = (double) estimator.counter.getTotalRowCount();
        if(value) {
            return estimator.counter.outputVariableValueCountTable.get(value)/totalRows;
        }
        return estimator.counter.outputVariableValueCountTable.get(!value)/totalRows;
    }

    double getLogProbability(DataRow row, Boolean outputYvalue, Double probabilityOfOutputYValue) {
        Double naiveJointLogDistribution = 1.0;
        for(int i=0; i<row.getInputX().size(); i++) {  // iterate input vars
            Qtable estimateTable = estimator.getEstimate(i);
            Boolean inputVarValue = row.getInputX(i);
            Double jointInputOutputProbability = getInputVarEstimate(inputVarValue, outputYvalue, estimateTable);
            Double normalizedProbability = jointInputOutputProbability/probabilityOfOutputYValue;
            naiveJointLogDistribution+= getLogProbability(normalizedProbability); // + instead of * because using log
            //System.out.println("jointInputOutputProbability="+jointInputOutputProbability + " " +
            //        "normalizedProbability="+normalizedProbability + " " + "naiveJointLogDistribution="+naiveJointLogDistribution);
        }
        return naiveJointLogDistribution;
    }

    /**
     * Use to stabilize joint proability productorial
     */
    double getLogProbability(Double probability) {
        Double logProbability = Math.log(probability);
        // System.out.println("probability="+probability + " logProbability="+logProbability);
        return logProbability;
    }

    // find the estimate from the table, in the cell corresponding to inputVarValue and outputVarValue
    private Double getInputVarEstimate(Boolean inputVarValue, Boolean outputYvalue, Qtable estimateTable) {
        Double cellEstimate = null;
        if(!inputVarValue&&!outputYvalue) {
            cellEstimate = estimateTable.getValueXzeroYzero();
        }
        if(inputVarValue&&!outputYvalue) {
            cellEstimate = estimateTable.getValueXoneYzero();
        }
        if(!inputVarValue&&outputYvalue) {
            cellEstimate = estimateTable.getValueXzeroYone();
        }
        if(inputVarValue&&outputYvalue) {
            cellEstimate = estimateTable.getValueXoneYone();
        }
        return cellEstimate;
    }

}
