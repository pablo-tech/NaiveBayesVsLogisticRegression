package com.onenow;

import java.util.ArrayList;
import java.util.List;

/**
 * Gradient descends for betas
 */
public class EstimatorLogistic implements EstimatorInterface {

    private List<Double> inputVarBetas;
    private List<Double> inputVarGradient;

    private Double learningRate;
    private Long numEpochs;

    private Dataset dataset;


    public EstimatorLogistic(Double learningRate, Long numEpochs) {
        this.learningRate = learningRate;
        this.numEpochs = numEpochs;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    /**
     * "Batch" Logistic Regrgession Algorithm
     */
    public void buildEstimate() {
        initBetas();
        for(int i=0; i<numEpochs; i++) {  // go over the input dataset many times
            initGradient();
            for(int j=0; j<getNumRows(); j++) {  // datapoint rows in the dataset
                DataRow row = dataset.getDataRows().get(j);
                for(int k=0; k<getNumInputColumns(); k++) {     // inputVar column in dataset
                    double currentGradient = inputVarGradient.get(k);
                    int inputVarX = getIntValue(row.getInputX(k));
                    int outputVarY = getIntValue(row.getOutputY());
                    double logisticParamZ = getObjectiveFunctionParameterZ(row);
                    double gradientDelta = getGradientDelta(inputVarX, outputVarY, logisticParamZ);
                    inputVarGradient.set(k, currentGradient+gradientDelta);
                }
            }
            // System.out.println("INPUT_VAR_GRADIENT="+inputVarGradient);
            updateBeta();
        }
    }

    private void updateBeta() {
        for(int j=0; j<getNumInputColumns(); j++) {
            double currentBeta = inputVarBetas.get(j);
            double currentGradient = inputVarGradient.get(j);
            double deltaBeta = learningRate*currentGradient;
            double newBeta = currentBeta+deltaBeta;
            inputVarBetas.set(j, newBeta);
        }
        // System.out.println("INPUT_VAR_BETA="+inputVarBetas);
    }

    private double getGradientDelta(int actualInputX, int actualOutputY, double logisticParamZ) {
        double probabilityY1 = getProbabilityY1(logisticParamZ);
        return actualInputX*(actualOutputY-probabilityY1);
    }

    public double getProbabilityY1(DataRow row) {
        return getProbabilityY1(getObjectiveFunctionParameterZ(row));
    }

    private double getProbabilityY1(double logisticParamZ) {
        Double probability = 1/(1+getExponent(logisticParamZ));

        return probability;
    }

    private double getExponent(Double z) {
        return Math.pow(Math.E, -z);
    }

    protected double getObjectiveFunctionParameterZ(DataRow row) {
        Double z = 0.0;

        for(int i=0; i<row.getInputX().size(); i++) {  // summation
            Boolean inputVarValue = row.getInputX(i);
            if(inputVarValue) { // inputVarValue=1; ignoring inputVarValue=0 as it cancels out
                z += getBetaEstimate(i);
            }
        }

        return z;
    }

    private int getIntValue(Boolean booleanValue) {
        if(booleanValue) {
            return 1;
        }
        return 0;
    }

    /**
     * Initial value for all inputVar/column betas is initialized
     */
    private void initBetas() {
        this.inputVarBetas = new ArrayList<>();
        for(int i=0; i<getNumInputColumns(); i++) {
            inputVarBetas.add(0.0);
        }
    }

    private void initGradient() {
        this.inputVarGradient = new ArrayList<>();
        for(int i=0; i<getNumInputColumns(); i++) {
            inputVarGradient.add(0.0);
        }
    }

    private int getNumRows() {
        return dataset.getDataRows().size();
    }
    private int getNumInputColumns() {
        return dataset.getDataRows().get(0).getInputX().size();
    }

    public Double getBetaEstimate(Integer inputVariableId) {
        return this.inputVarBetas.get(inputVariableId);
    }

    public List<Double> getInputVarBetas() {
        return inputVarBetas;
    }

    public List<Double> getInputVarGradient() {
        return inputVarGradient;
    }

    public String toString() {
        String s = getName() + " " + dataset.getPath() + "\n";
        s = s + "\tGIVEN_LEARNING_RATE="+ learningRate + " GIVEN_NUM_EPOCHS="+numEpochs + "\n";
        s = s + "\tINPUT_VAR_BETAS="+inputVarBetas + "\n";
        s = s + "\tINPUT_VAR_GRADIENT="+inputVarGradient;
        return s;
    }

    public String getName() {
        return "LogisticEstimation";
    }

}
